package com.elibrary.LibraLink.dtos;

import com.elibrary.LibraLink.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDTO {
    private int id;
    private String device_name;
    private LocalDateTime login_at;
    private int login_fail_count;
    private String ip_address;
    private String login_browser;
    private User user_id;
}
