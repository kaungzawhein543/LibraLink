package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="content" ,nullable = false)
    private String content;
    @Column(name="created_at")
    private LocalDateTime created_at  = LocalDateTime.now();
    @Column(name="status")
    private boolean status;
    @Column(name="icon_name")
    private String icon_name;
    @Column(name="seen_status")
    private boolean seen_status = false;
    @Column(name="read_status")
    private boolean read_status = false;
    @Column(name="url_link",nullable = false)
    private String url_link;

}
