package com.one.social_media.repository;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT new com.one.social_media.dto.response.UserResDto(r.userReferenced.id, r.userReferenced.name, r.userReferenced.email, r.userReferenced.dob, r.userReferenced.phone, r.userReferenced.avatar, r.userReferenced.coverImage, r.userReferenced.bio, r.userReferenced.createdAt, r.userReferenced.updatedAt, r.userReferenced.deletedAt, r.userReferenced.unblockedAt)" +
            "FROM User u, Relationship r, RelationshipType rt " +
            "WHERE u.id = :userId AND " +
            "r.userOwner.id = u.id AND " +
            "r.relationshipType.id = rt.id AND " +
            "r.relationshipType.id = :relationshipType AND " +
            "r.userOwner.id = :userId")
    List<UserResDto> findAllFriends(Long userId, Long relationshipType);
}
