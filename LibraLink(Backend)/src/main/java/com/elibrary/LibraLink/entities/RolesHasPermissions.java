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
@Table(name="roles_has_permissions")
public class RolesHasPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role_id;

    @ManyToOne
    @JoinColumn(name="permission_id")
    private Permission permission_id;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
}
