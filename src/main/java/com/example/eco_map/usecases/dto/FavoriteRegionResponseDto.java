package com.example.eco_map.usecases.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FavoriteRegionResponseDto {
    private UUID id;
    private String name;
    private CoordinatesResponseDto coordinatesResponseDto;
}
