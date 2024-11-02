package com.elibrary.LibraLink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;
    private String name;
    private LocalDateTime created_at;
    private int pages;
    private String preview_photo_path;
    private String book_path;
    private int social_share_count;
}
