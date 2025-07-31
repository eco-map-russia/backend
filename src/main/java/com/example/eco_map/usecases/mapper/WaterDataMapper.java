package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.usecases.dto.WaterMapDto;
import com.example.eco_map.usecases.dto.WaterRegionDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = GeometryToGeoJsonMapper.class)
public interface WaterDataMapper {
    WaterRegionDetailsDto toDto(WaterData waterData);

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "region.geom", target = "geoJson", qualifiedByName = "toGeoJson")
    WaterMapDto toMapDto(WaterData waterData);
}
