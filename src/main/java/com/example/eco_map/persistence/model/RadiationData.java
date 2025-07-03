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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "radiation_data")
@Getter
@Setter
@EqualsAndHashCode(exclude = "observationPoint")
@ToString(exclude = "observationPoint")
public class RadiationData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="observation_point_id")
    private ObservationPoint observationPoint;
    @Column(name="radiation_level")
    private Double radiationLevel;
    @Column(name="time")
    private LocalDateTime time;
}