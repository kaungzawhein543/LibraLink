package com.elibrary.LibraLink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailServiceDTO {
    private int id;
    private String service_name;
    private String smtp_server;
    private int port;
    private String username;
    private String password;
    private String api_key;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean current_use;
}
