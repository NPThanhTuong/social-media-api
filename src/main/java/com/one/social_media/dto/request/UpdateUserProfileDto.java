package com.one.social_media.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileDto {
    private String name;
    private String phone;
    private String avatar;
    private String coverImage;
    private String bio;
}
