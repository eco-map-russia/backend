package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.usecases.dto.NatureReserveRequestDto;
import com.example.eco_map.usecases.dto.NatureReserveResponseDto;
import com.example.eco_map.usecases.dto.PageNatureReserveResponseDto;
import com.example.eco_map.usecases.dto.UpdateNatureReserveRequestDto;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface NatureReservesService {
    Mono<List<NatureReserve>> getByRegion(Region region);

    Mono<NatureReserveResponseDto> addNatureReserve(NatureReserveRequestDto dto);

    Mono<PageNatureReserveResponseDto> getAllNatureReserves(Pageable pageable);

    Mono<NatureReserveResponseDto> updateNatureReserve(UUID id, UpdateNatureReserveRequestDto dto);

    Mono<Void> deleteNatureReserve(UUID id);
}
