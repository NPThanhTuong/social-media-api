package com.one.social_media.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    
    private LocalDateTime updatedAt;

    public Relationship(User userOwner, User userReferenced, RelationshipType relationshipType, LocalDateTime updatedAt) {
        this.id = new RelationshipKey(userOwner.getId(), userReferenced.getId());
        this.userOwner = userOwner;
        this.userReferenced = userReferenced;
        this.relationshipType = relationshipType;
        this.updatedAt = updatedAt;
    }

}

