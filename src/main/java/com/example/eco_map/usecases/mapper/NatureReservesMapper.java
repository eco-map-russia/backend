package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.usecases.dto.NatureReserveDetailsDto;
import com.example.eco_map.usecases.dto.NatureReservesDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NatureReservesMapper {
    NatureReserveDetailsDto toDto(NatureReserve natureReserve);

    List<NatureReserveDetailsDto> toDtoList(List<NatureReserve> natureReserves);

    default NatureReservesDto toWrapperReservesDto(List<NatureReserve> natureReserves) {
        NatureReservesDto dto = new NatureReservesDto();
        dto.setNatureReserveDetailsDtos(toDtoList(natureReserves));
        return dto;
    }
}
