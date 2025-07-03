package com.example.eco_map.usecases;

import com.example.eco_map.usecases.dto.RegionResponseDto;

import java.util.List;

public interface RegionService {
    List<RegionResponseDto> getAllRegions();
}
