package com.example.eco_map.usecases.impl;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.NatureReservesService;
import com.example.eco_map.usecases.RegionDetailsAggregator;
import com.example.eco_map.usecases.SoilDataService;
import com.example.eco_map.usecases.WaterDataService;
import com.example.eco_map.usecases.dto.NatureReservesDto;
import com.example.eco_map.usecases.dto.RegionDetailsDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
import com.example.eco_map.usecases.dto.WaterRegionDetailsDto;
import com.example.eco_map.usecases.mapper.NatureReservesMapper;
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
    private final NatureReservesService natureReservesService;
    private final NatureReservesMapper natureReservesMapper;

    @Override
    public Mono<RegionDetailsDto> buildDetails(Region region) {
        Mono<SoilRegionDetailsDto> soilDataMono = soilDataService.getLatestByRegion(region)
                .map(soilDataMapper::toDto);

        Mono<WaterRegionDetailsDto> waterDataMono = waterDataService.getLatestByRegion(region)
                .map(waterDataMapper::toDto);

        Mono<NatureReservesDto> natureReservesServiceMono = natureReservesService.getByRegion(region)
                .map(natureReservesMapper::toWrapperReservesDto);

        return Mono.zip(soilDataMono, waterDataMono, natureReservesServiceMono)
                .map(tuple -> new RegionDetailsDto()
                        .regionId(region.getId())
                        .name(region.getName())
                        .center(regionMapper.toCoordinatesDto(region))
                        .soilData(tuple.getT1())
                        .waterData(tuple.getT2())
                        .natureReserves(tuple.getT3())
                );
    }
}