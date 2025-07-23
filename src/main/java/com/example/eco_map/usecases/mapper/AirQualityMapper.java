package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.usecases.dto.AirQualityDto;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirQualityMapper {

    @Mappings({
            @Mapping(source = "current.time", target = "time", dateFormat = "yyyy-MM-dd'T'HH:mm"),
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

    @Named("timeToDate")
    default LocalDate map(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toLocalDate() : null;
    }
}
