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
 * SoilRegionDetailsDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class SoilRegionDetailsDto {

  private @Nullable Double chronicSoilPollutionPercent;

  private @Nullable Double landDegradationNeutralityIndex;

  public SoilRegionDetailsDto chronicSoilPollutionPercent(@Nullable Double chronicSoilPollutionPercent) {
    this.chronicSoilPollutionPercent = chronicSoilPollutionPercent;
    return this;
  }

  /**
   * Get chronicSoilPollutionPercent
   * @return chronicSoilPollutionPercent
   */
  
  @Schema(name = "chronicSoilPollutionPercent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chronicSoilPollutionPercent")
  public @Nullable Double getChronicSoilPollutionPercent() {
    return chronicSoilPollutionPercent;
  }

  public void setChronicSoilPollutionPercent(@Nullable Double chronicSoilPollutionPercent) {
    this.chronicSoilPollutionPercent = chronicSoilPollutionPercent;
  }

  public SoilRegionDetailsDto landDegradationNeutralityIndex(@Nullable Double landDegradationNeutralityIndex) {
    this.landDegradationNeutralityIndex = landDegradationNeutralityIndex;
    return this;
  }

  /**
   * Get landDegradationNeutralityIndex
   * @return landDegradationNeutralityIndex
   */
  
  @Schema(name = "landDegradationNeutralityIndex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("landDegradationNeutralityIndex")
  public @Nullable Double getLandDegradationNeutralityIndex() {
    return landDegradationNeutralityIndex;
  }

  public void setLandDegradationNeutralityIndex(@Nullable Double landDegradationNeutralityIndex) {
    this.landDegradationNeutralityIndex = landDegradationNeutralityIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoilRegionDetailsDto soilRegionDetailsDto = (SoilRegionDetailsDto) o;
    return Objects.equals(this.chronicSoilPollutionPercent, soilRegionDetailsDto.chronicSoilPollutionPercent) &&
        Objects.equals(this.landDegradationNeutralityIndex, soilRegionDetailsDto.landDegradationNeutralityIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chronicSoilPollutionPercent, landDegradationNeutralityIndex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SoilRegionDetailsDto {\n");
    sb.append("    chronicSoilPollutionPercent: ").append(toIndentedString(chronicSoilPollutionPercent)).append("\n");
    sb.append("    landDegradationNeutralityIndex: ").append(toIndentedString(landDegradationNeutralityIndex)).append("\n");
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

