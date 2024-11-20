package com.one.social_media.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResDto {
    Long id;
    String theme;
    Date createdAt;
    Date deletedAt;
    Set<UserResDto> users;
    Set<MessageResDto> messages;
}
