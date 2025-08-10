package com.example.eco_map.usecases.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCommentRequestDto {
    @NotBlank(message = "Message text can not be empty")
    String text;
}
