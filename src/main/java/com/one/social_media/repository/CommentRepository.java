package com.one.social_media.repository;

import com.one.social_media.entity.Comment;
import com.one.social_media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);

    List<Comment> findByPostIdAndDeletedAtIsNull(Long postId);

    List<Comment> findByPostIdAndParentCommentIsNullAndDeletedAtIsNull(Long postId);

    List<Comment> findByPost(Post post);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.replies WHERE c.post.id = :postId")
    List<Comment> findCommentsWithRepliesByPostId(@Param("postId") Long postId);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.replies WHERE c.id = :commentId")
    Optional<Comment> findCommentWithReplies(@Param("commentId") Long commentId);


    @Query("SELECT c FROM Comment c WHERE c.post.owner.id = :userId")
    List<Comment> findAllByUserId(@Param("userId") long userId);

    @Query("SELECT MONTH(c.createdAt) AS month, COUNT(c) AS totalComments " +
            "FROM Comment c WHERE YEAR(c.createdAt) = :year " +
            "GROUP BY MONTH(c.createdAt)")
    List<Object[]> countCommentsByMonth(@Param("year") int year);

}
