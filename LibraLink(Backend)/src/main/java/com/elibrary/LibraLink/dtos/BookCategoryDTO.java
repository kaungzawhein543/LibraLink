package com.elibrary.LibraLink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryDTO {
    private String bookId;
    private String bookName;
    private LocalDateTime created_at;
    private boolean status;
    private int pages;
    private String preview_photo_path;
    private String book_path;
    private int social_share_count;
    private int categoryId;
    private String categoryName;
}
