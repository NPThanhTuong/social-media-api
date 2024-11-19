package com.one.social_media.repository;

import com.one.social_media.entity.Image;
import com.one.social_media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
      List<Image> findByPost(Post post);

      @Query("SELECT i FROM Image i WHERE i.post.owner.id = :userId")
      List<Image> findAllImagesByUserId(@Param("userId") Long userId);
}
