package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirQualityDataRepository extends JpaRepository<AirQualityData, UUID> {

    @Query("SELECT a FROM AirQualityData a WHERE a.observationPoint = :point ORDER BY a.time DESC LIMIT 1")
    Optional<AirQualityData> findLatestByObservationPoint(@Param("point") ObservationPoint point);

    @Query(name = "AirQualityData.findHistoricalData", nativeQuery = true)
    List<AirQualityHistoricalResponseDto> findDailyAveragesAirQualityData(@Param("observationPointId") UUID observationPointId,
                                                                          @Param("start") LocalDate start,
                                                                          @Param("end") LocalDate end);
}
