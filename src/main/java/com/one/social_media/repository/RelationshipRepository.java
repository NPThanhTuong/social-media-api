package com.one.social_media.repository;

import com.one.social_media.entity.Relationship;
import com.one.social_media.entity.RelationshipKey;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, RelationshipKey> {
    @Query("SELECT COUNT(r) FROM Relationship r WHERE r.userOwner = :user AND r.relationshipType.id = 1")
    int countFriendsByUser(@Param("user") User user);

    List<Relationship> findAllByUserOwnerId(Long userId);

    @Query("SELECT r FROM Relationship r WHERE r.userOwner.id = :userId AND r.relationshipType.id = :relationshipType")
    List<Relationship> findByUserOwnerIdAndRelationshipTypeId(@Param("userId") Long userId, @Param("relationshipType") Long relationshipType);

    Optional<Relationship> findByIdAndRelationshipTypeId(RelationshipKey relationshipKey, Long relationshipTypeId);

}
