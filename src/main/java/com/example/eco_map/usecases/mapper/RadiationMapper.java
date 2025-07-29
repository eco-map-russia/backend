package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataDto;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RadiationMapper {
    RadiationDataDto toDto(RadiationData radiationData);

    @Mapping(source = "observationPoint.id", target = "pointId")
    @Mapping(source = "observationPoint.name", target = "pointName")
    @Mapping(source = "data", target = "coordinatesResponseDto", qualifiedByName = "toCoordinates")
    RadiationMapDto toMapDto(RadiationData data);

    @Named("toCoordinates")
    default CoordinatesResponseDto mapToCoordinates(RadiationData data) {
        if (data == null || data.getObservationPoint() == null || data.getObservationPoint().getCoordinates() == null) {
            return null;
        }
        return new CoordinatesResponseDto()
                .lat(data.getObservationPoint().getCoordinates().getY())
                .lon(data.getObservationPoint().getCoordinates().getX());
    }
}
