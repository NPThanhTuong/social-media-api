package com.one.social_media.dto.response;

import com.one.social_media.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResDto {
    Long id;
    String name;
    String email;
    Date dob;
    String phone;
    String avatar;
    String coverImage;
    String bio;
    Date createdAt;
    Date updatedAt;
    Date deletedAt;
    Date unblockedAt;

    @Enumerated(EnumType.STRING)
    UserStatus status;
}
