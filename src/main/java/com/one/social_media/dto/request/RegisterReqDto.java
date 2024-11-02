package com.one.social_media.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterReqDto {
    @NotBlank
    @Size(min = 3, max = 30, message = "USERNAME_INVALID")
    String name;

    @NotBlank
    @Email(message = "EMAIL_INVALID")
    String email;

    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    String password;

    @Pattern(regexp = "^(0|\\+84)(3|5|7|8|9)\\d{8}$", message = "PHONE_NUMBER_INVALID")
    String phone;
}
