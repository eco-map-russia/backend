package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
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
 * SoilMapDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class SoilMapDto implements GetMapLayerData200ResponseInner {

  private @Nullable UUID regionId;

  private @Nullable String regionName;

  private @Nullable BigDecimal chronicSoilPollutionPercent;

  private @Nullable BigDecimal landDegradationNeutralityIndex;

  private @Nullable String geoJson;

  public SoilMapDto regionId(@Nullable UUID regionId) {
    this.regionId = regionId;
    return this;
  }

  /**
   * Get regionId
   * @return regionId
   */
  @Valid 
  @Schema(name = "regionId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("regionId")
  public @Nullable UUID getRegionId() {
    return regionId;
  }

  public void setRegionId(@Nullable UUID regionId) {
    this.regionId = regionId;
  }

  public SoilMapDto regionName(@Nullable String regionName) {
    this.regionName = regionName;
    return this;
  }

  /**
   * Get regionName
   * @return regionName
   */
  
  @Schema(name = "regionName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("regionName")
  public @Nullable String getRegionName() {
    return regionName;
  }

  public void setRegionName(@Nullable String regionName) {
    this.regionName = regionName;
  }

  public SoilMapDto chronicSoilPollutionPercent(@Nullable BigDecimal chronicSoilPollutionPercent) {
    this.chronicSoilPollutionPercent = chronicSoilPollutionPercent;
    return this;
  }

  /**
   * Get chronicSoilPollutionPercent
   * @return chronicSoilPollutionPercent
   */
  @Valid 
  @Schema(name = "chronicSoilPollutionPercent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("chronicSoilPollutionPercent")
  public @Nullable BigDecimal getChronicSoilPollutionPercent() {
    return chronicSoilPollutionPercent;
  }

  public void setChronicSoilPollutionPercent(@Nullable BigDecimal chronicSoilPollutionPercent) {
    this.chronicSoilPollutionPercent = chronicSoilPollutionPercent;
  }

  public SoilMapDto landDegradationNeutralityIndex(@Nullable BigDecimal landDegradationNeutralityIndex) {
    this.landDegradationNeutralityIndex = landDegradationNeutralityIndex;
    return this;
  }

  /**
   * Get landDegradationNeutralityIndex
   * @return landDegradationNeutralityIndex
   */
  @Valid 
  @Schema(name = "landDegradationNeutralityIndex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("landDegradationNeutralityIndex")
  public @Nullable BigDecimal getLandDegradationNeutralityIndex() {
    return landDegradationNeutralityIndex;
  }

  public void setLandDegradationNeutralityIndex(@Nullable BigDecimal landDegradationNeutralityIndex) {
    this.landDegradationNeutralityIndex = landDegradationNeutralityIndex;
  }

  public SoilMapDto geoJson(@Nullable String geoJson) {
    this.geoJson = geoJson;
    return this;
  }

  /**
   * Get geoJson
   * @return geoJson
   */
  
  @Schema(name = "geoJson", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("geoJson")
  public @Nullable String getGeoJson() {
    return geoJson;
  }

  public void setGeoJson(@Nullable String geoJson) {
    this.geoJson = geoJson;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoilMapDto soilMapDto = (SoilMapDto) o;
    return Objects.equals(this.regionId, soilMapDto.regionId) &&
        Objects.equals(this.regionName, soilMapDto.regionName) &&
        Objects.equals(this.chronicSoilPollutionPercent, soilMapDto.chronicSoilPollutionPercent) &&
        Objects.equals(this.landDegradationNeutralityIndex, soilMapDto.landDegradationNeutralityIndex) &&
        Objects.equals(this.geoJson, soilMapDto.geoJson);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionId, regionName, chronicSoilPollutionPercent, landDegradationNeutralityIndex, geoJson);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SoilMapDto {\n");
    sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n");
    sb.append("    regionName: ").append(toIndentedString(regionName)).append("\n");
    sb.append("    chronicSoilPollutionPercent: ").append(toIndentedString(chronicSoilPollutionPercent)).append("\n");
    sb.append("    landDegradationNeutralityIndex: ").append(toIndentedString(landDegradationNeutralityIndex)).append("\n");
    sb.append("    geoJson: ").append(toIndentedString(geoJson)).append("\n");
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

