package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RadiationDataRepository extends JpaRepository<RadiationData, UUID> {
    Optional<RadiationData> findTopByObservationPointOrderByCreatedAtDesc(ObservationPoint observationPoint);

    @Query("""
                SELECT r FROM RadiationData r
                JOIN FETCH r.observationPoint op
                WHERE r.createdAt = (
                    SELECT MAX(r2.createdAt)
                    FROM RadiationData r2
                    WHERE r2.observationPoint.id = op.id
                )
            """)
    List<RadiationData> findLatestRadiationDataWithPoint();

    @EntityGraph(attributePaths = "observationPoint")
    Page<RadiationData> findAll(Pageable pageable);

    @Query("SELECT r FROM RadiationData r JOIN FETCH r.observationPoint WHERE r.id= :id")
    Optional<RadiationData> findByIdWithObservationPoint(@Param("id") UUID id);
}
