package com.one.social_media.entity;

import com.one.social_media.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> rooms = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caller", fetch = FetchType.LAZY)
    private Set<Call> calls = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOwner", fetch = FetchType.LAZY)
    private Set<Relationship> ownerRelationships = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReferenced", fetch = FetchType.LAZY)
    private Set<Relationship> referencedRelationships = new HashSet<>();

    public User(String email, String password, String phone) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(Role role, String name, Date dob, String phone, String email, String password, String avatar, String bio, String coverImage) {
        this.role = role;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
        this.coverImage = coverImage;
    }

    public User(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        status = UserStatus.OFFLINE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.getUsers().add(this);
    }
}
