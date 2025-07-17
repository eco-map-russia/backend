package com.example.eco_map.usecases.dto;

import java.util.UUID;

public record RegistrationResponseDto(
        UUID id,
        String email,
        String firstName,
        String lastname,
        String phone
) {
}
