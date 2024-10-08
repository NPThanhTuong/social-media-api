package com.one.social_media.repository;

import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}