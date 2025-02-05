package com.elibrary.LibraLink.dtos;

import com.elibrary.LibraLink.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRefreshTokenDTO {
    private int id;
    private UUID device_id;
    private Timestamp expired_at;
    private Timestamp created_at;
    private User user_id;
}
