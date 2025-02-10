package com.elibrary.LibraLink.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLogsDTO {
    private int id;
    private String error_message;
    private String error_type;
    private String error_path;
    private String status_code;
    private boolean fix_status;
    private LocalDateTime error_happen_at;
    private String additional_info;
}
