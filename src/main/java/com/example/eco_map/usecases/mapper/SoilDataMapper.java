package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.SoilDataRequestDto;
import com.example.eco_map.usecases.dto.SoilDataResponseDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SoilDataMapper {
    SoilRegionDetailsDto toDto(SoilData soilData);

    SoilData toEntity(SoilDataRequestDto requestDto);

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    SoilDataResponseDto toResponseDto(SoilData entity);
}
