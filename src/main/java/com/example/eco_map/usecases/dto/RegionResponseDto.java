package com.example.eco_map.usecases.dto;

import java.util.UUID;

public record RegionResponseDto(
        UUID id,
        String name
) {
}
