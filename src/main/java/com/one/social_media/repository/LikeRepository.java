package com.one.social_media.repository;

import com.one.social_media.entity.Like;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);
    Optional<List<Like>> findByPostId(Long id);
}
