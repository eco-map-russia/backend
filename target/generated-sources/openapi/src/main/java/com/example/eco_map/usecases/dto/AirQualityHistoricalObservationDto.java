package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.AirQualityHistoricalResponseDto;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
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
 * AirQualityHistoricalObservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class AirQualityHistoricalObservationDto {

  private @Nullable CoordinatesResponseDto coordinatesResponseDto;

  private @Nullable AirQualityHistoricalResponseDto airQualityHistoricalResponseDto;

  public AirQualityHistoricalObservationDto coordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
    this.coordinatesResponseDto = coordinatesResponseDto;
    return this;
  }

  /**
   * Get coordinatesResponseDto
   * @return coordinatesResponseDto
   */
  @Valid 
  @Schema(name = "coordinatesResponseDto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coordinatesResponseDto")
  public @Nullable CoordinatesResponseDto getCoordinatesResponseDto() {
    return coordinatesResponseDto;
  }

  public void setCoordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
    this.coordinatesResponseDto = coordinatesResponseDto;
  }

  public AirQualityHistoricalObservationDto airQualityHistoricalResponseDto(@Nullable AirQualityHistoricalResponseDto airQualityHistoricalResponseDto) {
    this.airQualityHistoricalResponseDto = airQualityHistoricalResponseDto;
    return this;
  }

  /**
   * Get airQualityHistoricalResponseDto
   * @return airQualityHistoricalResponseDto
   */
  @Valid 
  @Schema(name = "airQualityHistoricalResponseDto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("airQualityHistoricalResponseDto")
  public @Nullable AirQualityHistoricalResponseDto getAirQualityHistoricalResponseDto() {
    return airQualityHistoricalResponseDto;
  }

  public void setAirQualityHistoricalResponseDto(@Nullable AirQualityHistoricalResponseDto airQualityHistoricalResponseDto) {
    this.airQualityHistoricalResponseDto = airQualityHistoricalResponseDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AirQualityHistoricalObservationDto airQualityHistoricalObservationDto = (AirQualityHistoricalObservationDto) o;
    return Objects.equals(this.coordinatesResponseDto, airQualityHistoricalObservationDto.coordinatesResponseDto) &&
        Objects.equals(this.airQualityHistoricalResponseDto, airQualityHistoricalObservationDto.airQualityHistoricalResponseDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinatesResponseDto, airQualityHistoricalResponseDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AirQualityHistoricalObservationDto {\n");
    sb.append("    coordinatesResponseDto: ").append(toIndentedString(coordinatesResponseDto)).append("\n");
    sb.append("    airQualityHistoricalResponseDto: ").append(toIndentedString(airQualityHistoricalResponseDto)).append("\n");
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

