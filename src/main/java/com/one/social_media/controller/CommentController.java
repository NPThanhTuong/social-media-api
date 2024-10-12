package com.one.social_media.controller;

import com.one.social_media.dto.request.CommentReqDto;
import com.one.social_media.dto.request.UpdateCommentReqDto;
import com.one.social_media.dto.response.CommentResDto;
import com.one.social_media.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @GetMapping("post/{postId}")
    public ResponseEntity<List<CommentResDto>> getAllPostComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getPostComments(postId));
    }

    @PostMapping("post/{postId}")
    public ResponseEntity<CommentResDto> createPostComments(@PathVariable("postId") Long postId, @RequestBody CommentReqDto commentReqDto) {
        return ResponseEntity.ok(commentService.createPostComments(postId, commentReqDto));
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentResDto> updatePostComments(@PathVariable("commentId") Long commentId, @RequestBody UpdateCommentReqDto commentReqDto) {
        return ResponseEntity.ok(commentService.updateComments(commentId, commentReqDto));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deletePostComments(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.deleteComments(commentId));
    }
}
