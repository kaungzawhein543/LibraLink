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
@Table(name="users_liked_books")
public class users_liked_books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;

    @Column(name="liked_at")
    private LocalDateTime liked_at = LocalDateTime.now();
}
