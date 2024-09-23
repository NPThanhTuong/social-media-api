package com.one.social_media.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_call")  // Đổi tên bảng từ 'call' thành 'phone_call'
public class Call implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Date startedAt;
    private Date endedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User caller;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}

