package com.one.social_media.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class RelationshipKey implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long userOwnerId;
    private Long userReferencedId;

    // Getters and setters
}