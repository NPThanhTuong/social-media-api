package com.one.social_media.controller;

import com.one.social_media.entity.*;
import com.one.social_media.repository.CommentRepository;
import com.one.social_media.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dashboard/post")
@RequiredArgsConstructor
public class AdminPostController {
    private final PostService postService;
    private final UserService userService;
    private final ImageService imageService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final RelationshipService relationshipService;
    private final CommentRepository commentRepository;
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "30") int size,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postService.getAllPosts(pageable);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        return "post/list";
    }

    @GetMapping("/view/{post_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String view(Model model, @PathVariable("post_id") long postId) {

        Post post = postService.findById(postId).orElse(null);
        User user = userService.findById(post.getOwner().getId()).orElse(null);
        List<Image> images = imageService.findByPost(post);
        List<Like> likes = likeService.findByPostId(post.getId()).orElseGet(() -> new ArrayList<>());

        String gridClass = getGridClass(images.size());
        int totalPost = postService.totalPostOfUser(user);
        int totalFriend = relationshipService.totalFriendsOfUser(user);
        List<Comment> comments = commentRepository.findCommentsWithRepliesByPostId(postId);

        model.addAttribute("post", post);
        model.addAttribute("images", images);
        model.addAttribute("user", user);
        model.addAttribute("images", images);
        model.addAttribute("user", user);
        model.addAttribute("likes", likes);
        model.addAttribute("comments", comments);
        model.addAttribute("gridClass", gridClass);
        model.addAttribute("totalPost", totalPost);
        model.addAttribute("totalFriend", totalFriend);

        return "post/view";
    }

    private String getGridClass(int size) {
        return switch (size) {
            case 1 -> "one-image";
            case 2 -> "two-images";
            case 3 -> "three-images";
            case 4 -> "four-images";
            default -> "five-images";
        };
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Post post = postService.findById(id).orElse(null);
        if (post == null) {
            redirectAttributes.addFlashAttribute("error", "Bài viết không tồn tại!");
            return "redirect:/dashboard/post/list";
        }

        List<Image> images = imageService.findByPost(post);
        for (Image image : images) {
            String imagePath = "src/main/resources/static" + image.getPath();
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
            }
        }

        postService.deletePostById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa bài viết thành công!");
        return "redirect:/dashboard/post/list";
    }

    @GetMapping("/searchByDate")
    @PreAuthorize("hasRole('ADMIN')")
    public String searchPostsByDate(
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size,
            @RequestParam(required = false) String contentSearch,
            @RequestParam(required = false) String dateFilter,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postService.searchPostByDate(fromDate, toDate, pageable);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (fromDate != null) {
            LocalDate localFromDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            model.addAttribute("fromDate", localFromDate.format(formatter));
        } else {
            model.addAttribute("fromDate", null);
        }

        if (toDate != null) {
            LocalDate localToDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            model.addAttribute("toDate", localToDate.format(formatter));
        } else {
            model.addAttribute("toDate", null);
        }

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("contentSearch", contentSearch);
        model.addAttribute("contentSearch", contentSearch);

        return "post/list";
    }

    @GetMapping("/filterByDate")
    @PreAuthorize("hasRole('ADMIN')")
    public String filterPostsByDate(@RequestParam(value = "dateFilter", required = false) String dateFilter,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "30") int size,
                                    @RequestParam(required = false) String contentSearch,
                                    @RequestParam(required = false) String fromDate,
                                    @RequestParam(required = false) String toDate,
                                    Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts;

        if ("latest".equals(dateFilter)) {
            posts = postService.findAllSortedByDateDesc(pageable);
        } else if ("oldest".equals(dateFilter)) {
            posts = postService.findAllSortedByDateAsc(pageable);
        } else {
            posts = postService.getAllPosts(pageable);
        }


        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("contentSearch", contentSearch);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("dateFilter", dateFilter);

        return "post/list";
    }


    @GetMapping("/deleteComment/{post_id}/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable("post_id") long postId) {
        commentService.deleteCommentsByAdmin(commentId);
        return "redirect:/dashboard/post/view/" + postId;
    }


}
