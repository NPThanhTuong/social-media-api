package com.one.social_media.controller;

import com.one.social_media.dto.request.LikePostReqDto;
import com.one.social_media.dto.request.PostReqDto;
import com.one.social_media.dto.response.LikePostResDto;
import com.one.social_media.dto.response.PostResDto;
import com.one.social_media.mapper.PostMapper;
import com.one.social_media.service.PostService;
import com.one.social_media.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;
    UserService userService;
    PostMapper postMapper;


    @GetMapping
    public ResponseEntity<List<PostResDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllUserPosts());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("friends")
    public ResponseEntity<List<PostResDto>> getAllFriendPosts() {
        return ResponseEntity.ok(postService.getAllFriendPosts());
    }

    @PostMapping
    public ResponseEntity<PostResDto> createNewPost(@RequestBody PostReqDto postReqDto) {
        PostResDto createdPost = postMapper.toPostResDto(postService.createNewPost(postReqDto));

        return ResponseEntity.ok(createdPost);
    }

    @PostMapping("like")
    public ResponseEntity<LikePostResDto> likePost(@RequestBody LikePostReqDto likePostReqDto) {
        return ResponseEntity.ok(postService.likePost(likePostReqDto));
    }

    @GetMapping("liked")
    public ResponseEntity<List<PostResDto>> likedPosts() {
        return ResponseEntity.ok(postService.getLikedPost());
    }

    @GetMapping("liked/ids")
    public ResponseEntity<List<Long>> getListLikePostIds() {
        return ResponseEntity.ok(postService.getListLikePostIds());
    }
}
