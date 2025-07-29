package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RadiationMapDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class RadiationMapDto implements GetMapLayerData200ResponseInner {

  private @Nullable UUID pointId;

  private @Nullable String pointName;

  private @Nullable CoordinatesResponseDto coordinatesResponseDto;

  private @Nullable BigDecimal betaFallout;

  public RadiationMapDto pointId(@Nullable UUID pointId) {
    this.pointId = pointId;
    return this;
  }

  /**
   * Get pointId
   * @return pointId
   */
  @Valid 
  @Schema(name = "pointId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pointId")
  public @Nullable UUID getPointId() {
    return pointId;
  }

  public void setPointId(@Nullable UUID pointId) {
    this.pointId = pointId;
  }

  public RadiationMapDto pointName(@Nullable String pointName) {
    this.pointName = pointName;
    return this;
  }

  /**
   * Get pointName
   * @return pointName
   */
  
  @Schema(name = "pointName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pointName")
  public @Nullable String getPointName() {
    return pointName;
  }

  public void setPointName(@Nullable String pointName) {
    this.pointName = pointName;
  }

  public RadiationMapDto coordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
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

  public RadiationMapDto betaFallout(@Nullable BigDecimal betaFallout) {
    this.betaFallout = betaFallout;
    return this;
  }

  /**
   * Get betaFallout
   * @return betaFallout
   */
  @Valid 
  @Schema(name = "betaFallout", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("betaFallout")
  public @Nullable BigDecimal getBetaFallout() {
    return betaFallout;
  }

  public void setBetaFallout(@Nullable BigDecimal betaFallout) {
    this.betaFallout = betaFallout;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RadiationMapDto radiationMapDto = (RadiationMapDto) o;
    return Objects.equals(this.pointId, radiationMapDto.pointId) &&
        Objects.equals(this.pointName, radiationMapDto.pointName) &&
        Objects.equals(this.coordinatesResponseDto, radiationMapDto.coordinatesResponseDto) &&
        Objects.equals(this.betaFallout, radiationMapDto.betaFallout);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointId, pointName, coordinatesResponseDto, betaFallout);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RadiationMapDto {\n");
    sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
    sb.append("    pointName: ").append(toIndentedString(pointName)).append("\n");
    sb.append("    coordinatesResponseDto: ").append(toIndentedString(coordinatesResponseDto)).append("\n");
    sb.append("    betaFallout: ").append(toIndentedString(betaFallout)).append("\n");
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

