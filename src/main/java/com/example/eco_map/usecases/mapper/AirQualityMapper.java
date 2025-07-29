package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.usecases.dto.AirMapDto;
import com.example.eco_map.usecases.dto.AirQualityDto;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirQualityMapper {

    @Mappings({
            @Mapping(source = "current.time", target = "time", qualifiedByName = "stringToTime"),
            @Mapping(source = "current.pm10", target = "pm10"),
            @Mapping(source = "current.pm25", target = "pm25"),
            @Mapping(source = "current.carbonMonoxide", target = "carbonMonoxide"),
            @Mapping(source = "current.carbonDioxide", target = "carbonDioxide"),
            @Mapping(source = "current.nitrogenDioxide", target = "nitrogenDioxide"),
            @Mapping(source = "current.sulphurDioxide", target = "sulphurDioxide"),
            @Mapping(source = "current.ozone", target = "ozone"),
            @Mapping(source = "current.aerosolOpticalDepth", target = "aerosolOpticalDepth"),
            @Mapping(source = "current.dust", target = "dust"),
            @Mapping(source = "current.methane", target = "methane"),
            @Mapping(source = "current.europeanAqi", target = "europeanAqi")
    })
    AirQualityData dtoToEntity(AirQualityDto dto);

    AirQualityResponseDto entityToDto(AirQualityData entity);

    @Mapping(source = "time", target = "date", qualifiedByName = "timeToDate")
    AirQualityHistoricalResponseDto entityToHistoricalResponseDto(AirQualityData entity);

    @Mapping(source = "observationPoint.id", target = "pointId")
    @Mapping(source = "observationPoint.name", target = "pointName")
    @Mapping(source = "data", target = "coordinatesResponseDto", qualifiedByName = "toCoordinates")
    AirMapDto toMapDto(AirQualityData data);


    @Named("timeToDate")
    default LocalDate map(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.toLocalDate() : null;
    }

    @Named("stringToTime")
    default OffsetDateTime map(String value) {
        if (value == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(value, formatter);
        return localDateTime.atZone(ZoneId.of("Europe/Moscow")).toOffsetDateTime();
    }

    @Named("toCoordinates")
    default CoordinatesResponseDto mapToCoordinates(AirQualityData data) {
        if (data == null || data.getObservationPoint() == null || data.getObservationPoint().getCoordinates() == null) {
            return null;
        }
        return new CoordinatesResponseDto()
                .lat(data.getObservationPoint().getCoordinates().getY())
                .lon(data.getObservationPoint().getCoordinates().getX());
    }

}
