package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.RadiationDataDto;
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
 * RadiationDataObservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class RadiationDataObservationDto {

  private @Nullable CoordinatesResponseDto coordinatesResponseDto;

  private @Nullable RadiationDataDto radiationDataDto;

  public RadiationDataObservationDto coordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
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

  public RadiationDataObservationDto radiationDataDto(@Nullable RadiationDataDto radiationDataDto) {
    this.radiationDataDto = radiationDataDto;
    return this;
  }

  /**
   * Get radiationDataDto
   * @return radiationDataDto
   */
  @Valid 
  @Schema(name = "radiationDataDto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("radiationDataDto")
  public @Nullable RadiationDataDto getRadiationDataDto() {
    return radiationDataDto;
  }

  public void setRadiationDataDto(@Nullable RadiationDataDto radiationDataDto) {
    this.radiationDataDto = radiationDataDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RadiationDataObservationDto radiationDataObservationDto = (RadiationDataObservationDto) o;
    return Objects.equals(this.coordinatesResponseDto, radiationDataObservationDto.coordinatesResponseDto) &&
        Objects.equals(this.radiationDataDto, radiationDataObservationDto.radiationDataDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinatesResponseDto, radiationDataDto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RadiationDataObservationDto {\n");
    sb.append("    coordinatesResponseDto: ").append(toIndentedString(coordinatesResponseDto)).append("\n");
    sb.append("    radiationDataDto: ").append(toIndentedString(radiationDataDto)).append("\n");
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

