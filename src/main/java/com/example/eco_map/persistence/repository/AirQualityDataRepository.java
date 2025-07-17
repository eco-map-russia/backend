package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AirQualityDataRepository extends JpaRepository<AirQualityData, UUID> {

    @Query("SELECT a FROM AirQualityData a WHERE a.observationPoint = :point ORDER BY a.time DESC LIMIT 1")
    Optional<AirQualityData> findLatestByObservationPoint(@Param("point") ObservationPoint point);
}
