package com.one.social_media.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResDto {
    Long id;
    String content;
    Date createdAt;
    Date updatedAt;
    Date deletedAt;
    List<ImageResDto> images;
    UserResDto owner;
    int totalLike;
    int totalComment;

}
