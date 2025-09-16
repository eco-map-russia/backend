package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.WaterData;
import com.example.eco_map.usecases.dto.WaterMapDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WaterDataRepository extends JpaRepository<WaterData, UUID> {
    Optional<WaterData> findFirstByRegionOrderByCreatedAt(Region region);

    @Query(name = "WaterData.findLatestWaterDataWithRegion", nativeQuery = true)
    List<WaterMapDto> findLatestWaterDataWithRegion(Double tolerance);

    @EntityGraph(attributePaths = "region")
    Page<WaterData> findAll(Pageable pageable);

    @Query("SELECT w FROM WaterData w JOIN FETCH w.region r WHERE w.id = :id")
    Optional<WaterData> findByIdWithRegion(@Param("id") UUID id);
}
