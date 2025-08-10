package com.example.eco_map.usecases;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NatureReservesService {
    Mono<List<NatureReserve>> getByRegion(Region region);
}
