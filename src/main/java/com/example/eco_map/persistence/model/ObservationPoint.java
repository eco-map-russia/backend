package com.example.eco_map.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"airQualityData", "radiationData"})
@Getter
@Setter
@EqualsAndHashCode(exclude = {"airQualityData", "radiationData", "coordinates"})
@Table(name = "observation_points")
public class ObservationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    @Column(name = "coordinates", columnDefinition = "geometry(Point, 4326)")
    private Point coordinates;
    @OneToMany(mappedBy = "observationPoint", cascade = CascadeType.ALL)
    private List<AirQualityData> airQualityData = new ArrayList<>();
    @OneToMany(mappedBy = "observationPoint", cascade = CascadeType.ALL)
    private List<RadiationData> radiationData = new ArrayList<>();
}
