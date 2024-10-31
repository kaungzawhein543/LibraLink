package com.elibrary.LibraLink.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="ph_number")
    private String ph_number;
    @Column(name="status")
    private boolean status;
    @Column(name="date_of_birth")
    private Date DOB;
    @Column(name="profile_photo_path")
    private String profile_photo_path;
    @Column(name="notification_device_id")
    private String notification_device_id;
    @Column(name="email_noti_permission")
    private boolean email_noti_permission;
    @Column(name="oauth_provider")
    private String oauth_provider;
    @Column(name="oauth_id")
    private String oauth_id;
    @Column(name="theme")
    private String theme;
}
