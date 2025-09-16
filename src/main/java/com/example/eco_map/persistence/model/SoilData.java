package com.example.eco_map.persistence.model;

import com.example.eco_map.usecases.dto.SoilMapDto;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@NamedNativeQuery(
        name = "SoilData.findLatestSoilDataWithRegion",
        query = """
                SELECT
                    r.id AS regionId,
                    r.name AS regionName,
                    sd.chronic_soil_pollution_percent AS chronicSoilPollutionPercent,
                    sd.land_degradation_neutrality_index AS landDegradationNeutralityIndex,
                    ST_AsGeoJSON(ST_SimplifyPreserveTopology(r.geom, :tolerance)) AS geoJson
                FROM soil_data sd
                JOIN regions r ON sd.region_id = r.id
                WHERE sd.created_at = (
                    SELECT MAX(sd2.created_at)
                    FROM soil_data sd2
                    WHERE sd2.region_id = sd.region_id
                )
                """,
        resultSetMapping = "SoilDataMapping"
)
@SqlResultSetMapping(
        name = "SoilDataMapping",
        classes = @ConstructorResult(
                targetClass = SoilMapDto.class,
                columns = {
                        @ColumnResult(name = "regionId", type = UUID.class),
                        @ColumnResult(name = "regionName", type = String.class),
                        @ColumnResult(name = "chronicSoilPollutionPercent", type = Double.class),
                        @ColumnResult(name = "landDegradationNeutralityIndex", type = Double.class),
                        @ColumnResult(name = "geoJson", type = String.class)
                }
        )
)
@Table(name = "soil_data")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "region")
public class SoilData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    @Column(name = "chronic_soil_pollution_percent")
    private Double chronicSoilPollutionPercent;
    @Column(name = "land_degradation_neutrality_index")
    private Double landDegradationNeutralityIndex;
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : getClass();

        if (!thisEffectiveClass.equals(oEffectiveClass)) return false;

        SoilData that = (SoilData) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode()
                : getClass().hashCode();
    }
}
