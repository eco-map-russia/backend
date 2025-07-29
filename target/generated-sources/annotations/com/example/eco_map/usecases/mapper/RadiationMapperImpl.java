package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import com.example.eco_map.usecases.dto.RadiationDataDto;
import com.example.eco_map.usecases.dto.RadiationMapDto;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T11:29:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class RadiationMapperImpl implements RadiationMapper {

    @Override
    public RadiationDataDto toDto(RadiationData radiationData) {
        if ( radiationData == null ) {
            return null;
        }

        RadiationDataDto radiationDataDto = new RadiationDataDto();

        if ( radiationData.getBetaFallout() != null ) {
            radiationDataDto.setBetaFallout( BigDecimal.valueOf( radiationData.getBetaFallout() ) );
        }

        return radiationDataDto;
    }

    @Override
    public RadiationMapDto toMapDto(RadiationData data) {
        if ( data == null ) {
            return null;
        }

        RadiationMapDto radiationMapDto = new RadiationMapDto();

        radiationMapDto.setPointId( dataObservationPointId( data ) );
        radiationMapDto.setPointName( dataObservationPointName( data ) );
        radiationMapDto.setCoordinatesResponseDto( mapToCoordinates( data ) );
        if ( data.getBetaFallout() != null ) {
            radiationMapDto.setBetaFallout( BigDecimal.valueOf( data.getBetaFallout() ) );
        }

        return radiationMapDto;
    }

    private UUID dataObservationPointId(RadiationData radiationData) {
        if ( radiationData == null ) {
            return null;
        }
        ObservationPoint observationPoint = radiationData.getObservationPoint();
        if ( observationPoint == null ) {
            return null;
        }
        UUID id = observationPoint.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dataObservationPointName(RadiationData radiationData) {
        if ( radiationData == null ) {
            return null;
        }
        ObservationPoint observationPoint = radiationData.getObservationPoint();
        if ( observationPoint == null ) {
            return null;
        }
        String name = observationPoint.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
