package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.usecases.dto.CityDto;
import com.example.eco_map.usecases.dto.CleanupEventMapPointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CleanupEventRepository extends JpaRepository<CleanupEvent, UUID> {
    @Query("SELECT new com.example.eco_map.usecases.dto.CityDto(c.id, c.name) FROM City c")
    Page<CityDto> findAllWithoutCoordinates(Pageable pageable);


    @Query(nativeQuery = true, name = "CleanupEvent.mapDto")
    List<CleanupEventMapPointDto> findAllForMap();
}
