package com.example.eco_map.usecases.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentResponseDto {
    private UUID id;
    private UUID regionId;
    private UUID userId;
    private String username;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
