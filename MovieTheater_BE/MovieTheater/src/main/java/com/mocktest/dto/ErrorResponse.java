package com.mocktest.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    public LocalDateTime timestamp;
    public String error;
    public String status;
    public String message;
    public String path;
}
