package com.one.social_media.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private RelationshipKey id;

    @ManyToOne
    @MapsId("userOwnerId")
    @JoinColumn(name = "user_owner_id")
    private User userOwner;

    @ManyToOne
    @MapsId("userReferencedId")
    @JoinColumn(name = "user_referenced_id")
    private User userReferenced;

    @ManyToOne
    @JoinColumn(name = "relationship_type_id")
    private RelationshipType relationshipType;
    
    private Date updatedAt;

}

