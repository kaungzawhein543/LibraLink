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
@Table(name="email_services")
public class EmailService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="serivce_name")
    private String service_name;
    @Column(name="smtp_server")
    private String smtp_server;
    @Column(name="port")
    private int port;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="api_key")
    private String api_key;
    @Column(name="from_email")
    private String from_email;
    @Column(name="created_at")
    private LocalDateTime created_at;
    @Column(name="updated_at")
    private LocalDateTime updated_at;
    @Column(name="current_use")
    private boolean current_use;
}
