package com.elibrary.LibraLink.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="created_at")
    private LocalDateTime created_at  = LocalDateTime.now();
    @Column(name="status")
    private boolean status = true;
    @Column(name="pages")
    private int pages;
    @Column(name="preview_photo_path")
    private String preview_photo_path;
    @Column(name="book_path")
    private String book_path;
    @Column(name="social_share_count")
    private int social_share_count;
}
