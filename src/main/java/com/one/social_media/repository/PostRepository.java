package com.one.social_media.repository;

import com.one.social_media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByOwnerId(Long ownerId);


    @Query("SELECT p FROM Post p WHERE p.owner.id IN :friendIds")
    List<Post> findAllFriendPosts(List<Long> friendIds);
}
