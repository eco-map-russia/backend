package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.RegionDetailsAggregator;
import com.example.eco_map.usecases.SoilDataService;
import com.example.eco_map.usecases.WaterDataService;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
import com.example.eco_map.usecases.dto.WaterRegionDetailsDto;
import com.example.eco_map.usecases.mapper.RegionMapper;
import com.example.eco_map.usecases.mapper.SoilDataMapper;
import com.example.eco_map.usecases.mapper.WaterDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegionDetailsAggregatorImpl implements RegionDetailsAggregator {
    private final SoilDataService soilDataService;
    private final WaterDataService waterDataService;
    private final SoilDataMapper soilDataMapper;
    private final WaterDataMapper waterDataMapper;
    private final RegionMapper regionMapper;

    @Override
    public Mono<RegionDetailsDto> buildDetails(Region region) {
        Mono<SoilRegionDetailsDto> soilDataMono = soilDataService.getLatestByRegion(region)
                .map(soilDataMapper::toDto);

        Mono<WaterRegionDetailsDto> waterDataMono = waterDataService.getLatestByRegion(region)
                .map(waterDataMapper::toDto);

        return Mono.zip(soilDataMono, waterDataMono)
                .map(tuple -> new RegionDetailsDto()
                        .regionId(region.getId())
                        .name(region.getName())
                        .center(regionMapper.toCoordinatesDto(region))
                        .soilData(tuple.getT1())
                        .waterData(tuple.getT2())
                );
    }
}