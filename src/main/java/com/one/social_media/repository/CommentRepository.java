package com.one.social_media.repository;

import com.one.social_media.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    List<Comment> findByPostIdAndParentCommentIsNull(Long postId);
}
