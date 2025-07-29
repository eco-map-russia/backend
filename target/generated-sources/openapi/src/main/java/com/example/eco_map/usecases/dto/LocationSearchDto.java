package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LocationSearchDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class LocationSearchDto {

  private @Nullable UUID id;

  private @Nullable String name;

  private @Nullable BigDecimal lat;

  private @Nullable BigDecimal lon;

  public LocationSearchDto id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  public LocationSearchDto name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public LocationSearchDto lat(@Nullable BigDecimal lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   * @return lat
   */
  @Valid 
  @Schema(name = "lat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lat")
  public @Nullable BigDecimal getLat() {
    return lat;
  }

  public void setLat(@Nullable BigDecimal lat) {
    this.lat = lat;
  }

  public LocationSearchDto lon(@Nullable BigDecimal lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Get lon
   * @return lon
   */
  @Valid 
  @Schema(name = "lon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lon")
  public @Nullable BigDecimal getLon() {
    return lon;
  }

  public void setLon(@Nullable BigDecimal lon) {
    this.lon = lon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationSearchDto locationSearchDto = (LocationSearchDto) o;
    return Objects.equals(this.id, locationSearchDto.id) &&
        Objects.equals(this.name, locationSearchDto.name) &&
        Objects.equals(this.lat, locationSearchDto.lat) &&
        Objects.equals(this.lon, locationSearchDto.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationSearchDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

