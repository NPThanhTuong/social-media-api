package com.one.social_media.repository;

import com.one.social_media.dto.response.UserResDto;
import com.one.social_media.entity.Image;
import com.one.social_media.entity.User;
import com.one.social_media.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT new com.one.social_media.dto.response.UserResDto(r.userReferenced.id, r.userReferenced.name, r.userReferenced.email, r.userReferenced.dob, r.userReferenced.phone, r.userReferenced.avatar, r.userReferenced.coverImage, r.userReferenced.bio, r.userReferenced.createdAt, r.userReferenced.updatedAt, r.userReferenced.deletedAt, r.userReferenced.unblockedAt, r.userReferenced.status)" +
            "FROM User u, Relationship r, RelationshipType rt " +
            "WHERE u.id = :userId AND " +
            "r.userOwner.id = u.id AND " +
            "r.relationshipType.id = rt.id AND " +
            "r.relationshipType.id = :relationshipType AND " +
            "r.userOwner.id = :userId")
    List<UserResDto> findAllFriends(Long userId, Long relationshipType);

    @Query(value = "SELECT new com.one.social_media.dto.response.UserResDto(r.userReferenced.id, r.userReferenced.name, r.userReferenced.email, r.userReferenced.dob, r.userReferenced.phone, r.userReferenced.avatar, r.userReferenced.coverImage, r.userReferenced.bio, r.userReferenced.createdAt, r.userReferenced.updatedAt, r.userReferenced.deletedAt, r.userReferenced.unblockedAt, r.userReferenced.status)" +
            " FROM Relationship r " +
            " WHERE (r.userOwner.id = :userId) ")
    List<UserResDto> findAllRelationShipByUserOwner(@Param("userId") Long userId);


    @Query("SELECT new com.one.social_media.dto.response.UserResDto(u.id, u.name, u.email, u.dob, u.phone, u.avatar, u.coverImage, u.bio, u.createdAt, u.updatedAt, u.deletedAt, u.unblockedAt, u.status) " +
            "FROM User u " +
            "WHERE u.id != :userId " +
            "AND u.id NOT IN (SELECT r.userReferenced.id FROM Relationship r WHERE r.userOwner.id = :userId) ")
    List<UserResDto> findSuggestedFriends(@Param("userId") Long userId);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.rooms WHERE u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("SELECT MONTH(u.createdAt), COUNT(u) FROM User u WHERE YEAR(u.createdAt) = :year GROUP BY MONTH(u.createdAt)")
    List<Object[]> countNewUsersByMonth(@Param("year") int year);

    Boolean existsByEmail(String email);

    List<User> findAllByStatus(UserStatus userStatus);
    @Query("SELECT i FROM Image i WHERE i.post.owner.id = :userId")
    List<Image> findAllImagesByUserId(@Param("userId") Long userId);

}
