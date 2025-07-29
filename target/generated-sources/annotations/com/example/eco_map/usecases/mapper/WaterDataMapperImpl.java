package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.usecases.dto.WaterMapDto;
import com.example.eco_map.usecases.dto.WaterRegionDetailsDto;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.locationtech.jts.geom.MultiPolygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T11:29:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class WaterDataMapperImpl implements WaterDataMapper {

    @Autowired
    private GeometryToGeoJsonMapper geometryToGeoJsonMapper;

    @Override
    public WaterRegionDetailsDto toDto(WaterData waterData) {
        if ( waterData == null ) {
            return null;
        }

        WaterRegionDetailsDto waterRegionDetailsDto = new WaterRegionDetailsDto();

        waterRegionDetailsDto.setDirtySurfaceWaterPercent( waterData.getDirtySurfaceWaterPercent() );

        return waterRegionDetailsDto;
    }

    @Override
    public WaterMapDto toMapDto(WaterData waterData) {
        if ( waterData == null ) {
            return null;
        }

        WaterMapDto waterMapDto = new WaterMapDto();

        waterMapDto.setRegionId( waterDataRegionId( waterData ) );
        waterMapDto.setRegionName( waterDataRegionName( waterData ) );
        waterMapDto.setGeoJson( geometryToGeoJsonMapper.toGeoJson( waterDataRegionGeom( waterData ) ) );
        if ( waterData.getDirtySurfaceWaterPercent() != null ) {
            waterMapDto.setDirtySurfaceWaterPercent( BigDecimal.valueOf( waterData.getDirtySurfaceWaterPercent() ) );
        }

        return waterMapDto;
    }

    private UUID waterDataRegionId(WaterData waterData) {
        if ( waterData == null ) {
            return null;
        }
        Region region = waterData.getRegion();
        if ( region == null ) {
            return null;
        }
        UUID id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String waterDataRegionName(WaterData waterData) {
        if ( waterData == null ) {
            return null;
        }
        Region region = waterData.getRegion();
        if ( region == null ) {
            return null;
        }
        String name = region.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private MultiPolygon waterDataRegionGeom(WaterData waterData) {
        if ( waterData == null ) {
            return null;
        }
        Region region = waterData.getRegion();
        if ( region == null ) {
            return null;
        }
        MultiPolygon geom = region.getGeom();
        if ( geom == null ) {
            return null;
        }
        return geom;
    }
}
