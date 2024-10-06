package com.one.social_media.controller;
import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import com.one.social_media.service.ImageService;
import com.one.social_media.service.PostService;
import com.one.social_media.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/dashboard/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final ImageService imageService;


    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public String list(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/view/{post_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String view(Model model, @PathVariable("post_id") long postId) {
        Post post = postService.findById(postId).orElse(null);
        User user = userService.findById(post.getOwner().getId()).orElse(null);
        List<Image> images = imageService.findByPost(post);

        model.addAttribute("post", post);
        model.addAttribute("images", images);
        model.addAttribute("user", user);
        String gridClass = getGridClass(images.size());
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
