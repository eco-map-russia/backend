package com.example.eco_map.persistence.repository;

import com.example.eco_map.persistence.model.AirQualityData;
import com.example.eco_map.persistence.model.ObservationPoint;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AirQualityDataRepository extends JpaRepository<AirQualityData, UUID> {

    AirQualityData findTopByObservationPointOrderByTimeDesc(ObservationPoint point);

    @Query(name = "AirQualityData.findHistoricalData", nativeQuery = true)
    List<AirQualityHistoricalResponseDto> findDailyAveragesAirQualityData(@Param("observationPointId") UUID observationPointId,
                                                                          @Param("start") LocalDate start,
                                                                          @Param("end") LocalDate end);


    @Query("""
                SELECT a FROM AirQualityData a
                JOIN FETCH a.observationPoint op
                WHERE a.time = (
                    SELECT MAX(a2.time)
                    FROM AirQualityData a2
                    WHERE a2.observationPoint.id = op.id
                )
            """)
    List<AirQualityData> findLatestAirDataWithPoint();


}
