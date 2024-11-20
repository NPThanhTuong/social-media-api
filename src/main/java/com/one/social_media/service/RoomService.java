package com.one.social_media.service;

import com.one.social_media.dto.response.RoomResDto;
import com.one.social_media.entity.Room;
import com.one.social_media.entity.UserRoom;
import com.one.social_media.entity.UserRoomKey;
import com.one.social_media.exception.AppException;
import com.one.social_media.exception.ErrorCode;
import com.one.social_media.mapper.RoomMapper;
import com.one.social_media.repository.RoomRepository;
import com.one.social_media.repository.UserRepository;
import com.one.social_media.repository.UserRoomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomService {
    RoomRepository roomRepository;
    UserRepository userRepository;
    UserRoomRepository userRoomRepository;

    AuthService authService;

    RoomMapper roomMapper;

    public Optional<Long> getChatRoomId(
            Long senderId,
            Long recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return roomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Room::getId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChatRoom(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    @Transactional
    private Long createChatRoom(Long senderId, Long recipientId) {
        var sender = userRepository.findById(senderId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        var newChatRoom = new Room("light");

        roomRepository.save(newChatRoom);
        log.info("chat room id: {}", newChatRoom.getId());

        userRoomRepository.saveAll(List.of(
                new UserRoom(
                        new UserRoomKey(senderId, newChatRoom.getId()),
                        sender,
                        newChatRoom),
                new UserRoom(
                        new UserRoomKey(recipientId, newChatRoom.getId()),
                        recipient,
                        newChatRoom)
        ));

        return newChatRoom.getId();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<RoomResDto> getAllRooms() {
        var userId = authService.getLoginUserId();

        var rooms = roomRepository.findAllBySenderId(userId);


        var roomsRes = roomMapper.toListRoomResDto(rooms);

        return roomsRes;
    }
}
