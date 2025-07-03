package com.example.eco_map.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "air_quality_data")
@Getter
@Setter
@ToString(exclude = "observationPoint")
@EqualsAndHashCode(exclude = "observationPoint")
@AllArgsConstructor
@NoArgsConstructor
public class AirQualityData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_point_id")
    private ObservationPoint observationPoint;
    @Column(name = "pm25")
    private Double pm25;
    @Column(name = "pm10")
    private Double pm10;
    @Column(name = "time")
    private LocalDateTime time;
}