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
@Table(name="permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="permission_name",unique = true,nullable = false)
    private String permission_name;
    @Column(name="description")
    private String description;
    @Column(name="status")
    private boolean status;
    @Column(name="created_at")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(name="updated_at")
    private LocalDateTime updated_at;
}
