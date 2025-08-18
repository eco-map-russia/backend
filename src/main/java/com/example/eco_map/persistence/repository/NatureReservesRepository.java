package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.NatureReserve;
import com.example.eco_map.persistence.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NatureReservesRepository extends JpaRepository<NatureReserve, UUID> {
    List<NatureReserve> findByRegion(Region region);

    @EntityGraph(attributePaths = "region")
    Page<NatureReserve> findAll(Pageable pageable);

    @Query("SELECT n FROM NatureReserve n JOIN FETCH n.region r WHERE n.id = :id")
    Optional<NatureReserve> findByIdWithRegion(@Param("id") UUID id);
}
