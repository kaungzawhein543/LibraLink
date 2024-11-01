package com.elibrary.LibraLink.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="created_at")
    private LocalDateTime createed_at;
    @Column(name="pages")
    private int pages;
    @Column(name="preview_photo")
    private String preview_photo;
    @Column(name="book_path")
    private String book_path;
    @Column(name="social_share_count")
    private int social_share_count;
}
