package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="login_history")
public class LoginHistory {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name="id")
    private UUID id;
    @Column(name="device_name")
    private String device_name;
    @Column(name="login_at")
    private LocalDateTime login_at  = LocalDateTime.now();
    @Column(name="login_fail_count")
    private int  login_fail_count;
    @Column(name="ip_address")
    private String ip_address;
    @Column(name="status")
    private boolean status;
    @Column(name="login_browser")
    private String login_browser;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;
}
