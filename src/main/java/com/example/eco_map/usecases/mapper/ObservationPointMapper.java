package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.ObservationPointRequestDto;
import com.example.eco_map.usecases.dto.ObservationPointResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CoordinatesMapper.class)
public interface ObservationPointMapper {
    ObservationPoint toEntity(ObservationPointRequestDto requestDto);

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "city.name", target = "cityName")
    @Mapping(source = "coordinates", target = "coordinatesResponseDto", qualifiedByName = "toCoordinates")
    ObservationPointResponseDto toResponseDto(ObservationPoint entity);
}
