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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final ImageService imageService;
    private final CommentService commentService;
    private final LikeService likeService;

//    @GetMapping("/list")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String list(Model model) {
//        List<Post> posts = postService.getAllPosts();
//        model.addAttribute("posts", posts);
//        return "post/list";
//    }
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
        List<Comment> comments =commentService.findByPost(post);
        List<Like> likes = likeService.findByPostId(post.getId()).orElseGet(() -> new ArrayList<>());;
        String gridClass = getGridClass(images.size());

        model.addAttribute("post", post);
        model.addAttribute("images", images);
        model.addAttribute("user", user);
        model.addAttribute("images", images);
        model.addAttribute("user", user);
        model.addAttribute("likes", likes);
        model.addAttribute("comments", comments);
        model.addAttribute("gridClass", gridClass);

        return "post/view";
    }

    private String getGridClass(int size) {
        switch (size) {
            case 1: return "one-image";
            case 2: return "two-images";
            case 3: return "three-images";
            case 4: return "four-images";
            default: return "five-images"; // for 5 or more images
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return "redirect:/post/list";
    }

}
