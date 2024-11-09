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
@Table(name="users_has_connections")
public class UsersHasConnections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="userId_1")
    private User user1;

    @ManyToOne
    @JoinColumn(name="userId_2")
    private User user2;

    @Column(name="connected_at")
    private LocalDateTime connected_at;

    @Column(name="blocked_at")
    private LocalDateTime blocked_at;
}
