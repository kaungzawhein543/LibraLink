package com.elibrary.LibraLink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private int id;
    private String content;
    private LocalDateTime created_at;
    private boolean status;
    private String icon_name;
    private boolean seen_status;
    private boolean read_status;
    private String url_link;

}
