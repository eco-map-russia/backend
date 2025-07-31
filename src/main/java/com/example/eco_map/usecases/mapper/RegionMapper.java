package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = GeometryToGeoJsonMapper.class)
public interface RegionMapper {
    @Mapping(source = "geom", target = "geoJson", qualifiedByName = "toGeoJson")
    RegionResponseDto regionToRegionResponseDto(Region region);

    @Mapping(source = "center", target = "lat", qualifiedByName = "pointToXCoordinate")
    @Mapping(source = "center", target = "lon", qualifiedByName = "pointToYCoordinate")
    CoordinatesResponseDto toCoordinatesDto(Region region);

    @Named("pointToXCoordinate")
    default Double pointToXCoordinate(Point center) {
        return center.getX();
    }

    @Named("pointToYCoordinate")
    default Double pointToYCoordinate(Point center) {
        return center.getY();
    }

}
