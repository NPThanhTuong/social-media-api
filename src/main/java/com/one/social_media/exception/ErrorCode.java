package com.one.social_media.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED("Uncategorized error"),
    USER_EXISTED("Người dùng đã tồn tại"),
    USERNAME_INVALID("Tên hiển thị không hợp lệ"),
    PASSWORD_INVALID("Mật khẩu không hợp lệ"),
    CONFIRM_PASSWORD_INVALID("Mật khẩu xác nhận không hợp lệ"),
    EMAIL_INVALID("Email is invalid!"),
    USER_NOT_EXISTED("User is not found!"),
    UNAUTHENTICATED("User is unauthenticated!"),
    PHONE_NUMBER_INVALID("Phone number is invalid!"),
    REFRESH_TOKEN_EXPIRED("Refresh token was expired. Please make a new signin request"),
    ROLE_NOT_FOUND("Role is not found!"),
    JWT_TOKEN_INVALID("Token is invalid!"),
    POST_NOT_EXIST("Post is not exist"),
    RELATIONSHIP_TYPE_NOT_EXIST("RelationshipType not found"),
    RELATIONSHIP_NOT_EXIST("Relationship not found"),
    ROOM_IS_NOT_EXIST("Chat room is not exist");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}