package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name",unique = true,nullable = false)
    private String name;
    @Column(name="status")
    private boolean status;
    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();
}
