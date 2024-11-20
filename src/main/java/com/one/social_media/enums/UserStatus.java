package com.one.social_media.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");

    private final String displayText;

    UserStatus(String displayText) {
        this.displayText = displayText;
    }

    @JsonValue
    public String getDisplayText() {
        return displayText;
    }
}
