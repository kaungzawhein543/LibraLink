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
@Table  (name="error_logs")
public class ErrorLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="error_message" ,nullable = false)
    private String error_message;
    @Column(name="error_type")
    private String error_type;
    @Column(name="stack_trace")
    private String error_path;
    @Column(name="status_code",nullable = false)
    private String status_code;
    @Column(name="error_happen_at")
    private LocalDateTime error_happen_at  = LocalDateTime.now();
    @Column(name="fix_status ")
    private boolean fix_status;
    @Column(name="status ")
    private boolean status ;
    @Column(name="additional_info")
    private String additional_info;
}
