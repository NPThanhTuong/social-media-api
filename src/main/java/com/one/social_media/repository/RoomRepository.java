package com.one.social_media.repository;

import com.one.social_media.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN r.users u1 " +
            "JOIN r.users u2 " +
            "WHERE u1.id = :senderId " +
            "AND u2.id = :recipientId " +
            "AND SIZE(r.users) = 2")
    Optional<Room> findBySenderIdAndRecipientId(
            @Param("senderId") Long senderId,
            @Param("recipientId") Long recipientId);

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN FETCH r.messages m " +
            "JOIN FETCH r.users u " +
            "WHERE r.id " +
            "IN (SELECT r1.id FROM Room r1 JOIN r1.users u1 WHERE u1.id = :senderId) " +
            "AND u.id != :senderId"
    )
    List<Room> findAllBySenderId(
            @Param("senderId") Long senderId);
}
