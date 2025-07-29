package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirMapDto;
import com.example.eco_map.usecases.dto.AirQualityCurrentDto;
import com.example.eco_map.usecases.dto.AirQualityDto;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
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
public class AirQualityMapperImpl implements AirQualityMapper {

    @Override
    public AirQualityData dtoToEntity(AirQualityDto dto) {
        if ( dto == null ) {
            return null;
        }

        AirQualityData airQualityData = new AirQualityData();

        airQualityData.setTime( map( dtoCurrentTime( dto ) ) );
        airQualityData.setPm10( dtoCurrentPm10( dto ) );
        airQualityData.setPm25( dtoCurrentPm25( dto ) );
        airQualityData.setCarbonMonoxide( dtoCurrentCarbonMonoxide( dto ) );
        airQualityData.setCarbonDioxide( dtoCurrentCarbonDioxide( dto ) );
        airQualityData.setNitrogenDioxide( dtoCurrentNitrogenDioxide( dto ) );
        airQualityData.setSulphurDioxide( dtoCurrentSulphurDioxide( dto ) );
        airQualityData.setOzone( dtoCurrentOzone( dto ) );
        airQualityData.setAerosolOpticalDepth( dtoCurrentAerosolOpticalDepth( dto ) );
        airQualityData.setDust( dtoCurrentDust( dto ) );
        airQualityData.setMethane( dtoCurrentMethane( dto ) );
        airQualityData.setEuropeanAqi( dtoCurrentEuropeanAqi( dto ) );

        return airQualityData;
    }

    @Override
    public AirQualityResponseDto entityToDto(AirQualityData entity) {
        if ( entity == null ) {
            return null;
        }

        AirQualityResponseDto airQualityResponseDto = new AirQualityResponseDto();

        airQualityResponseDto.setTime( entity.getTime() );
        if ( entity.getPm10() != null ) {
            airQualityResponseDto.setPm10( BigDecimal.valueOf( entity.getPm10() ) );
        }
        if ( entity.getPm25() != null ) {
            airQualityResponseDto.setPm25( BigDecimal.valueOf( entity.getPm25() ) );
        }
        if ( entity.getCarbonMonoxide() != null ) {
            airQualityResponseDto.setCarbonMonoxide( BigDecimal.valueOf( entity.getCarbonMonoxide() ) );
        }
        if ( entity.getCarbonDioxide() != null ) {
            airQualityResponseDto.setCarbonDioxide( BigDecimal.valueOf( entity.getCarbonDioxide() ) );
        }
        if ( entity.getNitrogenDioxide() != null ) {
            airQualityResponseDto.setNitrogenDioxide( BigDecimal.valueOf( entity.getNitrogenDioxide() ) );
        }
        if ( entity.getSulphurDioxide() != null ) {
            airQualityResponseDto.setSulphurDioxide( BigDecimal.valueOf( entity.getSulphurDioxide() ) );
        }
        if ( entity.getOzone() != null ) {
            airQualityResponseDto.setOzone( BigDecimal.valueOf( entity.getOzone() ) );
        }
        if ( entity.getAerosolOpticalDepth() != null ) {
            airQualityResponseDto.setAerosolOpticalDepth( BigDecimal.valueOf( entity.getAerosolOpticalDepth() ) );
        }
        if ( entity.getDust() != null ) {
            airQualityResponseDto.setDust( BigDecimal.valueOf( entity.getDust() ) );
        }
        if ( entity.getMethane() != null ) {
            airQualityResponseDto.setMethane( BigDecimal.valueOf( entity.getMethane() ) );
        }
        if ( entity.getEuropeanAqi() != null ) {
            airQualityResponseDto.setEuropeanAqi( BigDecimal.valueOf( entity.getEuropeanAqi() ) );
        }

        return airQualityResponseDto;
    }

    @Override
    public AirQualityHistoricalResponseDto entityToHistoricalResponseDto(AirQualityData entity) {
        if ( entity == null ) {
            return null;
        }

        AirQualityHistoricalResponseDto airQualityHistoricalResponseDto = new AirQualityHistoricalResponseDto();

        airQualityHistoricalResponseDto.setDate( map( entity.getTime() ) );
        if ( entity.getPm10() != null ) {
            airQualityHistoricalResponseDto.setPm10( BigDecimal.valueOf( entity.getPm10() ) );
        }
        if ( entity.getPm25() != null ) {
            airQualityHistoricalResponseDto.setPm25( BigDecimal.valueOf( entity.getPm25() ) );
        }
        if ( entity.getCarbonMonoxide() != null ) {
            airQualityHistoricalResponseDto.setCarbonMonoxide( BigDecimal.valueOf( entity.getCarbonMonoxide() ) );
        }
        if ( entity.getCarbonDioxide() != null ) {
            airQualityHistoricalResponseDto.setCarbonDioxide( BigDecimal.valueOf( entity.getCarbonDioxide() ) );
        }
        if ( entity.getNitrogenDioxide() != null ) {
            airQualityHistoricalResponseDto.setNitrogenDioxide( BigDecimal.valueOf( entity.getNitrogenDioxide() ) );
        }
        if ( entity.getSulphurDioxide() != null ) {
            airQualityHistoricalResponseDto.setSulphurDioxide( BigDecimal.valueOf( entity.getSulphurDioxide() ) );
        }
        if ( entity.getOzone() != null ) {
            airQualityHistoricalResponseDto.setOzone( BigDecimal.valueOf( entity.getOzone() ) );
        }
        if ( entity.getAerosolOpticalDepth() != null ) {
            airQualityHistoricalResponseDto.setAerosolOpticalDepth( BigDecimal.valueOf( entity.getAerosolOpticalDepth() ) );
        }
        if ( entity.getDust() != null ) {
            airQualityHistoricalResponseDto.setDust( BigDecimal.valueOf( entity.getDust() ) );
        }
        if ( entity.getMethane() != null ) {
            airQualityHistoricalResponseDto.setMethane( BigDecimal.valueOf( entity.getMethane() ) );
        }
        if ( entity.getEuropeanAqi() != null ) {
            airQualityHistoricalResponseDto.setEuropeanAqi( BigDecimal.valueOf( entity.getEuropeanAqi() ) );
        }

        return airQualityHistoricalResponseDto;
    }

    @Override
    public AirMapDto toMapDto(AirQualityData data) {
        if ( data == null ) {
            return null;
        }

        AirMapDto airMapDto = new AirMapDto();

        airMapDto.setPointId( dataObservationPointId( data ) );
        airMapDto.setPointName( dataObservationPointName( data ) );
        airMapDto.setCoordinatesResponseDto( mapToCoordinates( data ) );
        if ( data.getPm25() != null ) {
            airMapDto.setPm25( BigDecimal.valueOf( data.getPm25() ) );
        }
        if ( data.getPm10() != null ) {
            airMapDto.setPm10( BigDecimal.valueOf( data.getPm10() ) );
        }
        if ( data.getCarbonMonoxide() != null ) {
            airMapDto.setCarbonMonoxide( BigDecimal.valueOf( data.getCarbonMonoxide() ) );
        }
        if ( data.getCarbonDioxide() != null ) {
            airMapDto.setCarbonDioxide( BigDecimal.valueOf( data.getCarbonDioxide() ) );
        }
        if ( data.getNitrogenDioxide() != null ) {
            airMapDto.setNitrogenDioxide( BigDecimal.valueOf( data.getNitrogenDioxide() ) );
        }
        if ( data.getSulphurDioxide() != null ) {
            airMapDto.setSulphurDioxide( BigDecimal.valueOf( data.getSulphurDioxide() ) );
        }
        if ( data.getOzone() != null ) {
            airMapDto.setOzone( BigDecimal.valueOf( data.getOzone() ) );
        }
        if ( data.getAerosolOpticalDepth() != null ) {
            airMapDto.setAerosolOpticalDepth( BigDecimal.valueOf( data.getAerosolOpticalDepth() ) );
        }
        if ( data.getDust() != null ) {
            airMapDto.setDust( BigDecimal.valueOf( data.getDust() ) );
        }
        if ( data.getMethane() != null ) {
            airMapDto.setMethane( BigDecimal.valueOf( data.getMethane() ) );
        }
        if ( data.getEuropeanAqi() != null ) {
            airMapDto.setEuropeanAqi( BigDecimal.valueOf( data.getEuropeanAqi() ) );
        }
        airMapDto.setTime( data.getTime() );

        return airMapDto;
    }

    private String dtoCurrentTime(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        String time = current.getTime();
        if ( time == null ) {
            return null;
        }
        return time;
    }

    private Double dtoCurrentPm10(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double pm10 = current.getPm10();
        if ( pm10 == null ) {
            return null;
        }
        return pm10;
    }

    private Double dtoCurrentPm25(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double pm25 = current.getPm25();
        if ( pm25 == null ) {
            return null;
        }
        return pm25;
    }

    private Double dtoCurrentCarbonMonoxide(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double carbonMonoxide = current.getCarbonMonoxide();
        if ( carbonMonoxide == null ) {
            return null;
        }
        return carbonMonoxide;
    }

    private Double dtoCurrentCarbonDioxide(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double carbonDioxide = current.getCarbonDioxide();
        if ( carbonDioxide == null ) {
            return null;
        }
        return carbonDioxide;
    }

    private Double dtoCurrentNitrogenDioxide(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double nitrogenDioxide = current.getNitrogenDioxide();
        if ( nitrogenDioxide == null ) {
            return null;
        }
        return nitrogenDioxide;
    }

    private Double dtoCurrentSulphurDioxide(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double sulphurDioxide = current.getSulphurDioxide();
        if ( sulphurDioxide == null ) {
            return null;
        }
        return sulphurDioxide;
    }

    private Double dtoCurrentOzone(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double ozone = current.getOzone();
        if ( ozone == null ) {
            return null;
        }
        return ozone;
    }

    private Double dtoCurrentAerosolOpticalDepth(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double aerosolOpticalDepth = current.getAerosolOpticalDepth();
        if ( aerosolOpticalDepth == null ) {
            return null;
        }
        return aerosolOpticalDepth;
    }

    private Double dtoCurrentDust(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double dust = current.getDust();
        if ( dust == null ) {
            return null;
        }
        return dust;
    }

    private Double dtoCurrentMethane(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double methane = current.getMethane();
        if ( methane == null ) {
            return null;
        }
        return methane;
    }

    private Double dtoCurrentEuropeanAqi(AirQualityDto airQualityDto) {
        if ( airQualityDto == null ) {
            return null;
        }
        AirQualityCurrentDto current = airQualityDto.getCurrent();
        if ( current == null ) {
            return null;
        }
        Double europeanAqi = current.getEuropeanAqi();
        if ( europeanAqi == null ) {
            return null;
        }
        return europeanAqi;
    }

    private UUID dataObservationPointId(AirQualityData airQualityData) {
        if ( airQualityData == null ) {
            return null;
        }
        ObservationPoint observationPoint = airQualityData.getObservationPoint();
        if ( observationPoint == null ) {
            return null;
        }
        UUID id = observationPoint.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String dataObservationPointName(AirQualityData airQualityData) {
        if ( airQualityData == null ) {
            return null;
        }
        ObservationPoint observationPoint = airQualityData.getObservationPoint();
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
