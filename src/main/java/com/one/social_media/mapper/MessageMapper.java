package com.one.social_media.mapper;

import com.one.social_media.dto.response.MessageResDto;
import com.one.social_media.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "sender.id", target = "senderId")
    MessageResDto toMessageResDto(Message message);

    List<MessageResDto> toListMessageResDto(List<Message> messages);
}
