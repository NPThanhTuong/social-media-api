package com.one.social_media.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Builder
@Data
public class RelationshipKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userOwnerId;
    private Long userReferencedId;

    public RelationshipKey() {
    }

    // Constructor có tham số
    public RelationshipKey(Long userOwnerId, Long userReferencedId) {
        this.userOwnerId = userOwnerId;
        this.userReferencedId = userReferencedId;
    }
    // Getters and setters
}