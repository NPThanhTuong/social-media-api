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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
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
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
//    private Set<Message> calls = new HashSet<>();
}

