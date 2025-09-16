package com.example.eco_map.persistence.model;

import com.example.eco_map.usecases.dto.WaterMapDto;
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
        name = "WaterData.findLatestWaterDataWithRegion",
        query = """
                SELECT
                    r.id AS regionId,
                    r.name AS regionName,
                    wd.dirty_surface_water_percent AS dirtySurfaceWaterPercent,
                    ST_AsGeoJSON(ST_SimplifyPreserveTopology(r.geom, :tolerance)) AS geoJson
                FROM water_data wd
                JOIN regions r ON wd.region_id = r.id
                WHERE wd.created_at = (
                    SELECT MAX(wd2.created_at)
                    FROM water_data wd2
                    WHERE wd2.region_id = wd.region_id
                )
                """,
        resultSetMapping = "WaterDataMapping"
)
@SqlResultSetMapping(
        name = "WaterDataMapping",
        classes = @ConstructorResult(
                targetClass = WaterMapDto.class,
                columns = {
                        @ColumnResult(name = "regionId", type = UUID.class),
                        @ColumnResult(name = "regionName", type = String.class),
                        @ColumnResult(name = "dirtySurfaceWaterPercent", type = Double.class),
                        @ColumnResult(name = "geoJson", type = String.class)
                }
        )
)
@Entity
@Table(name = "water_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "region")
public class WaterData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "dirty_surface_water_percent")
    private Double dirtySurfaceWaterPercent;

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
        WaterData that = (WaterData) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
