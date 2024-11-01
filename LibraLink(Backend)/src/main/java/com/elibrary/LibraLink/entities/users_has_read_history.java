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
@Table(name="users_has_read_history")
public class users_has_read_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="last_read_at")
    private LocalDateTime last_read_at;

    @Column(name="last_page_read")
    private int last_page_read;
}