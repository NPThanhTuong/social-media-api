package com.one.social_media.repository;

import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByOwnerId(Long ownerId);

    @Query("SELECT p " +
            "FROM Post p " +
            "LEFT JOIN FETCH p.images " +
            "LEFT JOIN FETCH p.likes " +
            "LEFT JOIN FETCH p.comments " +
            "WHERE p.owner.id IN :friendIds")
    List<Post> findAllFriendPosts(List<Long> friendIds);

    @Query(value = "SELECT COUNT(p) FROM Post p WHERE p.owner = :user")
    int countByUser(@Param("user") User user);
}
