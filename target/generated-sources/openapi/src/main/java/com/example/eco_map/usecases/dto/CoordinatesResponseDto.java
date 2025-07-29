package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CoordinatesResponseDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class CoordinatesResponseDto {

  private @Nullable Double lat;

  private @Nullable Double lon;

  public CoordinatesResponseDto lat(@Nullable Double lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   * @return lat
   */
  
  @Schema(name = "lat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lat")
  public @Nullable Double getLat() {
    return lat;
  }

  public void setLat(@Nullable Double lat) {
    this.lat = lat;
  }

  public CoordinatesResponseDto lon(@Nullable Double lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Get lon
   * @return lon
   */
  
  @Schema(name = "lon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lon")
  public @Nullable Double getLon() {
    return lon;
  }

  public void setLon(@Nullable Double lon) {
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
    CoordinatesResponseDto coordinatesResponseDto = (CoordinatesResponseDto) o;
    return Objects.equals(this.lat, coordinatesResponseDto.lat) &&
        Objects.equals(this.lon, coordinatesResponseDto.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CoordinatesResponseDto {\n");
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

