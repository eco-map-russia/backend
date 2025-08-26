package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.usecases.dto.CleanupEventCreateRequestDto;
import com.example.eco_map.usecases.dto.CleanupEventDetailsDto;
import com.example.eco_map.usecases.dto.CleanupEventMapDto;
import com.example.eco_map.usecases.dto.CleanupEventMapPointDto;
import com.example.eco_map.usecases.dto.CleanupEventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CleanupEventMapper {
    @Mapping(source = "lat", target = "coordinatesResponseDto.lat")
    @Mapping(source = "lon", target = "coordinatesResponseDto.lon")
    CleanupEventMapDto toDto(CleanupEventMapPointDto dto);

    CleanupEventDetailsDto toDto(CleanupEvent event);

    CleanupEvent toEntity(CleanupEventCreateRequestDto requestDto);

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    CleanupEventResponseDto toResponseDto(CleanupEvent entity);

}
