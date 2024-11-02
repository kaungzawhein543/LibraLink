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
@Table(name = "users_download_books")
public class users_download_books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user_id;

    @Column(name="download_at" ,nullable = false)
    private LocalDateTime download_at = LocalDateTime.now();
}
