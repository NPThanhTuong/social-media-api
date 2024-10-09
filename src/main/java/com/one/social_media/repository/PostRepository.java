package com.one.social_media.repository;

import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT COUNT(p) FROM Post p WHERE p.owner = :user")
    int countByUser(@Param("user") User user);
}
