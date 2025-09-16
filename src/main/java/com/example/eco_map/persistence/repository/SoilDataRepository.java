package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.Region;
import com.example.eco_map.persistence.model.SoilData;
import com.example.eco_map.usecases.dto.SoilMapDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SoilDataRepository extends JpaRepository<SoilData, UUID> {
    Optional<SoilData> findFirstByRegionOrderByCreatedAtDesc(Region region);

    @Query(name = "SoilData.findLatestSoilDataWithRegion", nativeQuery = true)
    List<SoilMapDto> findLatestSoilDataWithRegion(Double tolerance);

    @Query("SELECT s FROM SoilData s LEFT JOIN FETCH s.region r")
    List<SoilData> findAllWithRegion();

    @EntityGraph(attributePaths = "region")
    Page<SoilData> findAll(Pageable pageable);

    @Query("SELECT s FROM SoilData s JOIN FETCH s.region r WHERE s.id = :id")
    Optional<SoilData> findByIdWithRegion(@Param("id") UUID id);
}
