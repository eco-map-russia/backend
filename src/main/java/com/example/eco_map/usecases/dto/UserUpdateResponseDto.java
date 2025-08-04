package com.example.eco_map.usecases.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateResponseDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
