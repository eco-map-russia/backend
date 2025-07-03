package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.usecases.dto.AirQualityCurrentDto;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AirQualityMapper {


    @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd'T'HH:mm")
    AirQualityData dtoToEntity(AirQualityCurrentDto dto);

    AirQualityResponseDto entityToDto(AirQualityData dto);
}
