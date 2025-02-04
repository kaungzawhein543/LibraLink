package com.elibrary.LibraLink.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="username",unique = true,nullable = false)
    private String username;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="status")
    private boolean status;
    @Column(name="date_of_birth",nullable = false)
    private LocalDate date_of_birth;
    @Column(name="profile_photo_path")
    private String profile_photo_path;
    @Column(name="notification_device_id")
    private String notification_device_id;
    @Column(name="email_notification_permission")
    private boolean email_notification_permission;
    @Column(name="oauth_provider")
    private String oauth_provider;
    @Column(name="oauth_id")
    private String oauth_id;
    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(name="theme")
    private String theme = "light";
    @Column(name="refresh_token")
    private String refresh_token;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role_id;

}
