package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.SoilMapDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
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
public class SoilDataMapperImpl implements SoilDataMapper {

    @Autowired
    private GeometryToGeoJsonMapper geometryToGeoJsonMapper;

    @Override
    public SoilRegionDetailsDto toDto(SoilData soilData) {
        if ( soilData == null ) {
            return null;
        }

        SoilRegionDetailsDto soilRegionDetailsDto = new SoilRegionDetailsDto();

        soilRegionDetailsDto.setChronicSoilPollutionPercent( soilData.getChronicSoilPollutionPercent() );
        soilRegionDetailsDto.setLandDegradationNeutralityIndex( soilData.getLandDegradationNeutralityIndex() );

        return soilRegionDetailsDto;
    }

    @Override
    public SoilMapDto toMapDto(SoilData soilData) {
        if ( soilData == null ) {
            return null;
        }

        SoilMapDto soilMapDto = new SoilMapDto();

        soilMapDto.setRegionId( soilDataRegionId( soilData ) );
        soilMapDto.setRegionName( soilDataRegionName( soilData ) );
        soilMapDto.setGeoJson( geometryToGeoJsonMapper.toGeoJson( soilDataRegionGeom( soilData ) ) );
        if ( soilData.getChronicSoilPollutionPercent() != null ) {
            soilMapDto.setChronicSoilPollutionPercent( BigDecimal.valueOf( soilData.getChronicSoilPollutionPercent() ) );
        }
        if ( soilData.getLandDegradationNeutralityIndex() != null ) {
            soilMapDto.setLandDegradationNeutralityIndex( BigDecimal.valueOf( soilData.getLandDegradationNeutralityIndex() ) );
        }

        return soilMapDto;
    }

    private UUID soilDataRegionId(SoilData soilData) {
        if ( soilData == null ) {
            return null;
        }
        Region region = soilData.getRegion();
        if ( region == null ) {
            return null;
        }
        UUID id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String soilDataRegionName(SoilData soilData) {
        if ( soilData == null ) {
            return null;
        }
        Region region = soilData.getRegion();
        if ( region == null ) {
            return null;
        }
        String name = region.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private MultiPolygon soilDataRegionGeom(SoilData soilData) {
        if ( soilData == null ) {
            return null;
        }
        Region region = soilData.getRegion();
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
