package com.one.social_media.entity;

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
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private Date createdAt;
    private Date deletedAt;

    @ManyToMany(mappedBy = "rooms")
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    public Room(String theme) {
        this.theme = theme.toUpperCase();
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
//    private Set<Message> calls = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.getRooms().add(this);
    }
}

