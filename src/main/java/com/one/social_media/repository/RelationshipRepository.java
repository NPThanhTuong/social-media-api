package com.one.social_media.repository;

import com.one.social_media.entity.Relationship;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("SELECT COUNT(r) FROM Relationship r WHERE r.userOwner = :user AND r.relationshipType.id = 1")
    int countFriendsByUser(@Param("user") User user);
}
