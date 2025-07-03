package com.example.eco_map.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.MultiPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "regions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "points")
@ToString(exclude = "points")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon, 4326)")
    private MultiPolygon geom;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<ObservationPoint> points = new ArrayList<>();

}
