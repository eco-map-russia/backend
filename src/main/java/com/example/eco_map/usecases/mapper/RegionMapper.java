package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.RegionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper {

    RegionResponseDto regionToRegionResponseDto(Region region);

}
