package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.usecases.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageMapper {
    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageCommentResponseDto mapToPageComments(Page<CommentResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageFavoriteRegionResponseDto mapToPageFavoriteRegions(Page<FavoriteRegionResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageSoilDataResponseDto mapToPageSoilDataResponses(Page<SoilDataResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageWaterDataResponseDto mapToPageWaterDataResponse(Page<WaterDataResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageRadiationDataResponseDto mapToPageRadiationDataResponse(Page<RadiationDataResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageNatureReserveResponseDto mapToPageNatureReserveResponses(Page<NatureReserveResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageObservationPointResponseDto mapToPageObservationPointResponse(Page<ObservationPointResponseDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageRegionResponseDto mapToPageRegionResponse(Page<RegionDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageCityResponseDto mapToPageCityResponse(Page<CityDto> page);

    @Mapping(target = "content", source = "page.content")
    @Mapping(target = "number", source = "page.number")
    @Mapping(target = "size", source = "page.size")
    @Mapping(target = "totalElements", source = "page.totalElements")
    @Mapping(target = "totalPages", source = "page.totalPages")
    @Mapping(target = "first", source = "page.first")
    @Mapping(target = "last", source = "page.last")
    @Mapping(target = "empty", source = "page.empty")
    PageCleanupEventResponseDto mapToPageCleanupEventsResponse(Page<CleanupEventResponseDto> page);
}
