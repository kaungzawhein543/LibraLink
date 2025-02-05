package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_refresh_tokens")
public class UserRefreshTokens {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name="device_id")
    private UUID device_id;
    @Column(name="refresh_token")
    private String token;
    @Column(name="expired_at")
    private Timestamp expired_at;
    @Column(name="created_at")
    private Timestamp created_at;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
