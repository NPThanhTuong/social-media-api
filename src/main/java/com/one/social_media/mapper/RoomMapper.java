package com.one.social_media.mapper;

import com.one.social_media.dto.response.RoomResDto;
import com.one.social_media.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomResDto toRoomResDto(Room room);

    List<RoomResDto> toListRoomResDto(List<Room> rooms);
}
