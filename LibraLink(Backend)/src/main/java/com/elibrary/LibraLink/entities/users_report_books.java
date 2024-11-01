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
@Table(name="users_report_book")
public class users_report_books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;

    @Column(name="reported_at")
    private LocalDateTime reported_at;
}
