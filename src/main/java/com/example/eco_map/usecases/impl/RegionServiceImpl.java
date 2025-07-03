package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.repository.RegionRepository;
import com.example.eco_map.usecases.RegionService;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import com.example.eco_map.usecases.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public List<RegionResponseDto> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream().map(regionMapper::regionToRegionResponseDto).toList();
    }

}
