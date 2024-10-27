package com.one.social_media.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResDto {
    String token;
    String type = "Bearer";
    Long id;
    String name;
    String avatar;
    String email;
    String role;

    public JwtResDto(String accessToken, Long id, String email, String name, String avatar, String role) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
    }
}
