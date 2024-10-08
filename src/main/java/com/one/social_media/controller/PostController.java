package com.one.social_media.controller;

import com.one.social_media.dto.response.CommentResDto;
import com.one.social_media.dto.response.PostResDto;
import com.one.social_media.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResDto>> getAllPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getAllUserPosts(userId));
    }

    @GetMapping("user/{userId}/friends")
    public ResponseEntity<List<PostResDto>> getAllFriendPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getAllFriendPosts(userId));
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentResDto>> getAllPostComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostComments(postId));
    }
}
