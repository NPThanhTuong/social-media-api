package com.one.social_media.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date dob;
    private String phone;
    private String avatar;
    private String coverImage;
    private String bio;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Date unblockedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_room", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caller")
    private Set<Call> calls = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOwner")
    private Set<Relationship> ownerRelationships = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReferenced")
    private Set<Relationship> referencedRelationships = new HashSet<>();
}
