package com.one.social_media.repository;

import com.one.social_media.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    List<Comment> findByPostIdAndDeletedAtIsNull(Long postId);

    List<Comment> findByPostIdAndParentCommentIsNullAndDeletedAtIsNull(Long postId);
}
