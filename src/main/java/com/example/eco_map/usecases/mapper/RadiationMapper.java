package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.RadiationDataDto;
import com.example.eco_map.usecases.dto.RadiationDataRequestDto;
import com.example.eco_map.usecases.dto.RadiationDataResponseDto;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CoordinatesMapper.class)
public interface RadiationMapper {
    RadiationDataDto toDto(RadiationData radiationData);

    @Mapping(source = "observationPoint.id", target = "pointId")
    @Mapping(source = "observationPoint.name", target = "pointName")
    @Mapping(source = "data", target = "coordinatesResponseDto", qualifiedByName = "toCoordinatesFromRadiation")
    RadiationMapDto toMapDto(RadiationData data);

    RadiationData toEntity(RadiationDataRequestDto requestDto);

    @Mapping(source = "observationPoint.id", target = "pointId")
    @Mapping(source = "observationPoint.name", target = "pointName")
    @Mapping(source = "data", target = "coordinatesResponseDto", qualifiedByName = "toCoordinatesFromRadiation")
    RadiationDataResponseDto toResponseDto(RadiationData data);
}
