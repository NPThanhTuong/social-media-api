package com.one.social_media.repository;

import com.one.social_media.entity.UserRoom;
import com.one.social_media.entity.UserRoomKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomKey> {
}
