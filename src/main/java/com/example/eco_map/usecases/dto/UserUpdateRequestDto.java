package com.example.eco_map.usecases.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

    @Size(min = 1, max = 50, message = "First name must be between {min} and {max} characters long")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name must be between {min} and {max} characters long")
    private String lastName;

    @Pattern(
            regexp = "^\\+?[0-9\\-\\s()]{7,15}$",
            message = "Invalid phone number format"
    )
    private String phone;
}