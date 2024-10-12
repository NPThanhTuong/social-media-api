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


    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResDto>> getAllPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getAllUserPosts(userId));
    }

    @GetMapping("user/{userId}/friends")
    public ResponseEntity<List<PostResDto>> getAllFriendPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getAllFriendPosts(userId));
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<PostResDto> createNewPost(@PathVariable("userId") Long userId, @RequestBody PostReqDto postReqDto) {
        PostResDto createdPost = postMapper.toPostResDto(postService.createNewPost(postReqDto, userId));

        return ResponseEntity.ok(createdPost);
    }

    @PostMapping("user/{userId}/like")
    public ResponseEntity<LikePostResDto> likePost(@PathVariable("userId") Long userId, @RequestBody LikePostReqDto likePostReqDto) {
        return ResponseEntity.ok(postService.likePost(userId, likePostReqDto));
    }

    @GetMapping("user/{userId}/liked")
    public ResponseEntity<List<PostResDto>> likedPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getLikedPost(userId));
    }

    @GetMapping("user/{userId}/liked/id")
    public ResponseEntity<List<Long>> getListLikePostIds(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(postService.getListLikePostIds(userId));
    }
}
