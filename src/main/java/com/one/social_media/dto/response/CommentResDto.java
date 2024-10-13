package com.one.social_media.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResDto {
    Long id;
    String content;
    Date createdAt;
    Date updatedAt;
    Date deletedAt;
    Long postId;
    UserResDto user;
    Long parentCommentId;
    List<CommentResDto> replies = new ArrayList<>();
}
