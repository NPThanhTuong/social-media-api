package com.one.social_media.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResDto {
    Long id;
    String content;
    Date sentAt;
    boolean isRead;

    Long roomId;
    Long senderId;
}
