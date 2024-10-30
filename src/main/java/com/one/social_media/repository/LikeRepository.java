package com.one.social_media.repository;

import com.one.social_media.entity.Like;
import com.one.social_media.entity.LikeKey;
import com.one.social_media.entity.Post;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeKey> {
    Optional<Like> findById(LikeKey likeKey);

    @Query("SELECT p FROM Post p " +
            "JOIN FETCH p.likes l " +
            "JOIN l.user u " +
            "WHERE u.id = :userId")
    List<Post> findAllPostByUserId(@Param("userId") Long userId);


    Optional<List<Like>> findByPostId(Long id);

    @Query("SELECT l FROM Like l WHERE l.post.owner.id = :userId")
    List<Like> findAllByUserId(@Param("userId") long userId);


    @Query("SELECT MONTH(l.createdAt), COUNT(l) FROM Like l WHERE YEAR(l.createdAt) = :year GROUP BY MONTH(l.createdAt)")
    List<Object[]> countLikesByMonth(@Param("year") int year);
}


