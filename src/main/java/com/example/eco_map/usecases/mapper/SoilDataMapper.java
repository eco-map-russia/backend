package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.SoilMapDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = GeometryToGeoJsonMapper.class)
public interface SoilDataMapper {
    SoilRegionDetailsDto toDto(SoilData soilData);

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "region.geom", target = "geoJson", qualifiedByName = "toGeoJson")
    SoilMapDto toMapDto(SoilData soilData);

}
