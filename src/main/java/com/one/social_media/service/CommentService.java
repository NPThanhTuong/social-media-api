package com.one.social_media.service;


import com.one.social_media.dto.request.CommentReqDto;
import com.one.social_media.dto.request.UpdateCommentReqDto;
import com.one.social_media.dto.response.CommentResDto;
import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.CommentMapper;
import com.one.social_media.repository.CommentRepository;
import com.one.social_media.repository.PostRepository;
import com.one.social_media.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    AuthService authService;

    CommentMapper commentMapper;

    @Transactional
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<CommentResDto> getPostComments(Long postId) {
        var comments = filterDeletedReplies(commentRepository
                .findByPostIdAndParentCommentIsNullAndDeletedAtIsNull(postId));

        return commentMapper.toListCommentResDto(comments);
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommentResDto createPostComments(Long postId, CommentReqDto commentReqDto) {
        long userId = authService.getLoginUserId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXIST));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Comment parentComment;
        if (commentReqDto.getParentId() == null) {
            parentComment = null;
        } else {
            parentComment = commentRepository.getReferenceById(commentReqDto.getParentId());
        }

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(commentReqDto.getContent())
                .parentComment(parentComment)
                .build();
        return commentMapper.toCommentResDto(commentRepository.save(comment));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommentResDto updateComments(Long commentId, UpdateCommentReqDto commentReqDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not found!"));

        comment.setContent(commentReqDto.getContent());

        return commentMapper.toCommentResDto(commentRepository.save(comment));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String deleteComments(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Not found!"));

        softDeleteRecursively(comment);

        return "Delete successfully";
    }

    private void softDeleteRecursively(Comment comment) {
        // Set deletedAt for the parent comment
        comment.setDeletedAt(new Date());
        commentRepository.save(comment);

        // Recursively soft delete all replies
        for (Comment reply : comment.getReplies()) {
            softDeleteRecursively(reply);
        }
    }

    private List<Comment> filterDeletedReplies(List<Comment> comments) {
        return comments.stream()
                .map(comment -> {
                    // Recursively filter replies
                    List<Comment> filteredReplies = filterDeletedReplies(comment.getReplies());
                    comment.setReplies(filteredReplies);
                    return comment;
                })
                .filter(comment -> comment.getDeletedAt() == null) // Ensure parent is not deleted
                .collect(Collectors.toList());
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
