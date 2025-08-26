package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.CleanupEvent;
import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.usecases.dto.CityDto;
import com.example.eco_map.usecases.dto.CleanupEventMapPointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CleanupEventRepository extends JpaRepository<CleanupEvent, UUID> {
    @EntityGraph(attributePaths ="city")
    Page<CleanupEvent> findAll(Pageable pageable);

    @Query(nativeQuery = true, name = "CleanupEvent.mapDto")
    List<CleanupEventMapPointDto> findAllForMap();

    @Query("SELECT cl FROM CleanupEvent cl JOIN FETCH cl.city c WHERE cl.id = :id")
    Optional<CleanupEvent> findByIdWithCity(@Param("id") UUID id);
}
