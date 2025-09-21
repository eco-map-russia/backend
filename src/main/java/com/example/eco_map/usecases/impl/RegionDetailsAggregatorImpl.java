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
import reactor.util.function.Tuple3;

import java.util.Optional;

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
        var soilDataMono = soilDataService.getLatestByRegion(region)
                .map(optSoilData -> optSoilData.map(soilDataMapper::toDto));

        var waterDataMono = waterDataService.getLatestByRegion(region)
                .map(optWaterData -> optWaterData.map(waterDataMapper::toDto));

        Mono<NatureReservesDto> natureReservesMono = natureReservesService.getByRegion(region)
                .map(natureReservesMapper::toWrapperReservesDto);

        return Mono.zip(soilDataMono, waterDataMono, natureReservesMono)
                .map(tuple -> mapToRegionDetails(tuple, region));

    }

    private RegionDetailsDto mapToRegionDetails(
            Tuple3<Optional<SoilRegionDetailsDto>, Optional<WaterRegionDetailsDto>, NatureReservesDto> tuple,
            Region region) {
        return new RegionDetailsDto()
                .regionId(region.getId())
                .name(region.getName())
                .center(regionMapper.toCoordinatesDto(region))
                .soilData(tuple.getT1().orElse(null))
                .waterData(tuple.getT2().orElse(null))
                .natureReserves(tuple.getT3());
    }
}