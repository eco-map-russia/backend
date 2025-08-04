package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.FavoriteRegion;
import com.example.eco_map.usecases.dto.FavoriteRegionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = RegionMapper.class)
public interface FavoriteRegionsMapper {
    @Mapping(source = "region.name", target = "name")
    @Mapping(source = "region", target = "coordinatesResponseDto")
    FavoriteRegionResponseDto toDto(FavoriteRegion favoriteRegion);
}
