package com.elibrary.LibraLink.dtos;

import com.elibrary.LibraLink.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private boolean status;
    private LocalDateTime DOB;
    private String profile_photo_path;
    private String notification_device_id;
    private boolean email_notification_permission;
    private String oauth_provider;
    private String oauth_id;
    private LocalDateTime created_at;
    private String theme;

    private Role role_id;
}
