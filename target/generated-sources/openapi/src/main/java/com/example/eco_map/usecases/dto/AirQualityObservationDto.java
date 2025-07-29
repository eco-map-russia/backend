package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.AirQualityResponseDto;
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
 * AirQualityObservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class AirQualityObservationDto {

  private @Nullable CoordinatesResponseDto coordinatesResponseDto;

  private @Nullable AirQualityResponseDto airQualityData;

  public AirQualityObservationDto coordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
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

  public AirQualityObservationDto airQualityData(@Nullable AirQualityResponseDto airQualityData) {
    this.airQualityData = airQualityData;
    return this;
  }

  /**
   * Get airQualityData
   * @return airQualityData
   */
  @Valid 
  @Schema(name = "airQualityData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("airQualityData")
  public @Nullable AirQualityResponseDto getAirQualityData() {
    return airQualityData;
  }

  public void setAirQualityData(@Nullable AirQualityResponseDto airQualityData) {
    this.airQualityData = airQualityData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AirQualityObservationDto airQualityObservationDto = (AirQualityObservationDto) o;
    return Objects.equals(this.coordinatesResponseDto, airQualityObservationDto.coordinatesResponseDto) &&
        Objects.equals(this.airQualityData, airQualityObservationDto.airQualityData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinatesResponseDto, airQualityData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AirQualityObservationDto {\n");
    sb.append("    coordinatesResponseDto: ").append(toIndentedString(coordinatesResponseDto)).append("\n");
    sb.append("    airQualityData: ").append(toIndentedString(airQualityData)).append("\n");
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

