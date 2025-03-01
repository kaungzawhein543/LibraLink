package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
public class UserRefreshTokens {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name="device_id")
    private UUID device_id;
    @Column(name="refresh_token", columnDefinition = "TEXT")
    private String token;
    @Column(name="expired_at")
    private LocalDateTime expired_at;
    @Column(name="created_at")
    private LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
