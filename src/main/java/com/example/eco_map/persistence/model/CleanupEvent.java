package com.example.eco_map.persistence.model;

import com.example.eco_map.usecases.dto.CleanupEventMapPointDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NamedNativeQuery(
        name = "CleanupEvent.mapDto",
        query = """
                  SELECT ce.id AS id,
                         c.name AS cityName,
                         ce.location AS location,
                         ce.event_date AS date,
                         ST_Y(c.center) AS lat,
                         ST_X(c.center) AS lon
                  FROM cleanup_events ce
                  JOIN cities c ON ce.city_id = c.id
                WHERE event_date>=CURRENT_DATE
                """,
        resultSetMapping = "CleanupEventMapDtoMapping"
)
@SqlResultSetMapping(
        name = "CleanupEventMapDtoMapping",
        classes = @ConstructorResult(
                targetClass = CleanupEventMapPointDto.class,
                columns = {
                        @ColumnResult(name = "id", type = java.util.UUID.class),
                        @ColumnResult(name = "cityName", type = String.class),
                        @ColumnResult(name = "location", type = String.class),
                        @ColumnResult(name = "date", type = java.time.LocalDate.class),
                        @ColumnResult(name = "lat", type = Double.class),
                        @ColumnResult(name = "lon", type = Double.class)
                }
        )
)

@Entity
@Table(name = "cleanup_events")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CleanupEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false, updatable = false)
    private City city;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "event_date", nullable = false)
    private LocalDate date;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "participants_expected")
    private Integer participantsExpected;

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

        CleanupEvent that = (CleanupEvent) o;

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
