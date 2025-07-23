package com.example.eco_map.usecases.dto;

import java.util.UUID;

public record LocationSearchDto(
        UUID id,
        String name,
        Double lat,
        Double lon
) {
}