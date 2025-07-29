package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T11:29:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Autowired
    private GeometryToGeoJsonMapper geometryToGeoJsonMapper;

    @Override
    public RegionResponseDto regionToRegionResponseDto(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionResponseDto regionResponseDto = new RegionResponseDto();

        regionResponseDto.setGeoJson( geometryToGeoJsonMapper.toGeoJson( region.getGeom() ) );
        regionResponseDto.setId( region.getId() );
        regionResponseDto.setName( region.getName() );

        return regionResponseDto;
    }

    @Override
    public CoordinatesResponseDto toCoordinatesDto(Region region) {
        if ( region == null ) {
            return null;
        }

        CoordinatesResponseDto coordinatesResponseDto = new CoordinatesResponseDto();

        coordinatesResponseDto.setLat( pointToXCoordinate( region.getCenter() ) );
        coordinatesResponseDto.setLon( pointToYCoordinate( region.getCenter() ) );

        return coordinatesResponseDto;
    }
}
