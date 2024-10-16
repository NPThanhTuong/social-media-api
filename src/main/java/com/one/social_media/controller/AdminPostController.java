package com.one.social_media.controller;

import com.one.social_media.entity.*;
import com.one.social_media.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
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
        List<Comment> comments = commentService.findByPost(post);
        List<Like> likes = likeService.findByPostId(post.getId()).orElseGet(() -> new ArrayList<>());
        ;
        String gridClass = getGridClass(images.size());
        int totalPost = postService.totalPostOfUser(user);
        int totalFriend = relationshipService.totalFriendsOfUser(user);

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


}
