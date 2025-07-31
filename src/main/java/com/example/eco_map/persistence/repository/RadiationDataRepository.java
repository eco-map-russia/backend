package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.persistence.model.RadiationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}
