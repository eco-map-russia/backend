package com.example.eco_map.persistence.model;

import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
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
import java.time.OffsetDateTime;
import java.util.UUID;

@NamedNativeQuery(
        name = "AirQualityData.findHistoricalData",
        query = """
                            SELECT
                                              DATE(time) AS date,
                                              AVG(pm10) AS pm10,
                                              AVG(pm25) AS pm25,
                                              AVG(carbon_monoxide) AS carbon_monoxide,
                                              AVG(carbon_dioxide) AS carbon_dioxide,
                                              AVG(nitrogen_dioxide) AS nitrogen_dioxide,
                                              AVG(sulphur_dioxide) AS sulphur_dioxide,
                                              AVG(ozone) AS ozone,
                                              AVG(aerosol_optical_depth) AS aerosol_optical_depth,
                                              AVG(dust) AS dust,
                                              AVG(methane) AS methane,
                                              AVG(european_aqi) AS european_aqi
                                          FROM air_quality_data
                                          WHERE DATE(time) BETWEEN :start AND :end
                                            AND observation_point_id = :observationPointId
                                          GROUP BY DATE(time)
                                          ORDER BY date;
                """,
        resultSetMapping = "AirQualityMapping"
)
@SqlResultSetMapping(
        name = "AirQualityMapping",
        classes = @ConstructorResult(
                targetClass = AirQualityHistoricalResponseDto.class,
                columns = {
                        @ColumnResult(name = "date", type = java.time.LocalDate.class),
                        @ColumnResult(name = "pm10", type = Double.class),
                        @ColumnResult(name = "pm25", type = Double.class),
                        @ColumnResult(name = "carbon_monoxide", type = Double.class),
                        @ColumnResult(name = "carbon_dioxide", type = Double.class),
                        @ColumnResult(name = "nitrogen_dioxide", type = Double.class),
                        @ColumnResult(name = "sulphur_dioxide", type = Double.class),
                        @ColumnResult(name = "ozone", type = Double.class),
                        @ColumnResult(name = "aerosol_optical_depth", type = Double.class),
                        @ColumnResult(name = "dust", type = Double.class),
                        @ColumnResult(name = "methane", type = Double.class),
                        @ColumnResult(name = "european_aqi", type = Double.class)
                }
        )
)
@Entity
@Table(name = "air_quality_data")
@Getter
@Setter
@ToString(exclude = "observationPoint")
@AllArgsConstructor
@NoArgsConstructor
public class AirQualityData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_point_id")
    private ObservationPoint observationPoint;
    @Column(name = "pm25")
    private Double pm25;

    @Column(name = "pm10")
    private Double pm10;

    @Column(name = "carbon_monoxide")
    private Double carbonMonoxide;

    @Column(name = "carbon_dioxide")
    private Double carbonDioxide;

    @Column(name = "nitrogen_dioxide")
    private Double nitrogenDioxide;

    @Column(name = "sulphur_dioxide")
    private Double sulphurDioxide;

    @Column(name = "ozone")
    private Double ozone;

    @Column(name = "aerosol_optical_depth")
    private Double aerosolOpticalDepth;

    @Column(name = "dust")
    private Double dust;

    @Column(name = "methane")
    private Double methane;

    @Column(name = "european_aqi")
    private Double europeanAqi;
    @Column(name = "time")
    private OffsetDateTime time;
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

        AirQualityData that = (AirQualityData) o;

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

