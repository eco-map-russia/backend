package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.usecases.dto.NatureReserveDetailsDto;
import com.example.eco_map.usecases.dto.NatureReserveRequestDto;
import com.example.eco_map.usecases.dto.NatureReserveResponseDto;
import com.example.eco_map.usecases.dto.NatureReservesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NatureReservesMapper {
    NatureReserveDetailsDto toDto(NatureReserve natureReserve);

    List<NatureReserveDetailsDto> toDtoList(List<NatureReserve> natureReserves);

    NatureReserve toEntity(NatureReserveRequestDto requestDto);

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    NatureReserveResponseDto toResponseDto(NatureReserve entity);

    default NatureReservesDto toWrapperReservesDto(List<NatureReserve> natureReserves) {
        NatureReservesDto dto = new NatureReservesDto();
        dto.setNatureReserveDetailsDtos(toDtoList(natureReserves));
        return dto;
    }
}
