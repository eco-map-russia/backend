package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesMapper {
    @Named("toCoordinates")
    public CoordinatesResponseDto toCoordinates(Point point) {
        if (point == null) {
            return null;
        }
        return new CoordinatesResponseDto()
                .lat(point.getY())
                .lon(point.getX());
    }

    @Named("toCoordinatesFromObservationPoint")
    public CoordinatesResponseDto fromObservationPoint(ObservationPoint op) {
        if (op == null) {
            return null;
        }
        return toCoordinates(op.getCoordinates());
    }

    @Named("toCoordinatesFromRadiation")
    public CoordinatesResponseDto mapFromRadiation(RadiationData data) {
        return fromObservationPoint(data == null ? null : data.getObservationPoint());
    }
}
