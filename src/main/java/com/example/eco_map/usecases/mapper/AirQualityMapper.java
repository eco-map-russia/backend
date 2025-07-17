package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.usecases.dto.AirQualityDto;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirQualityMapper {

    @Mapping(source = "current.time", target = "time", dateFormat = "yyyy-MM-dd'T'HH:mm")
    @Mapping(source = "current.pm10", target = "pm10")
    @Mapping(source = "current.pm25", target = "pm25")
    AirQualityData dtoToEntity(AirQualityDto dto);

    AirQualityResponseDto entityToDto(AirQualityData entity);
}
