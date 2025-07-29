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
 * WaterMapDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class WaterMapDto implements GetMapLayerData200ResponseInner {

  private @Nullable UUID regionId;

  private @Nullable String regionName;

  private @Nullable BigDecimal dirtySurfaceWaterPercent;

  private @Nullable String geoJson;

  public WaterMapDto regionId(@Nullable UUID regionId) {
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

  public WaterMapDto regionName(@Nullable String regionName) {
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

  public WaterMapDto dirtySurfaceWaterPercent(@Nullable BigDecimal dirtySurfaceWaterPercent) {
    this.dirtySurfaceWaterPercent = dirtySurfaceWaterPercent;
    return this;
  }

  /**
   * Get dirtySurfaceWaterPercent
   * @return dirtySurfaceWaterPercent
   */
  @Valid 
  @Schema(name = "dirtySurfaceWaterPercent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dirtySurfaceWaterPercent")
  public @Nullable BigDecimal getDirtySurfaceWaterPercent() {
    return dirtySurfaceWaterPercent;
  }

  public void setDirtySurfaceWaterPercent(@Nullable BigDecimal dirtySurfaceWaterPercent) {
    this.dirtySurfaceWaterPercent = dirtySurfaceWaterPercent;
  }

  public WaterMapDto geoJson(@Nullable String geoJson) {
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
    WaterMapDto waterMapDto = (WaterMapDto) o;
    return Objects.equals(this.regionId, waterMapDto.regionId) &&
        Objects.equals(this.regionName, waterMapDto.regionName) &&
        Objects.equals(this.dirtySurfaceWaterPercent, waterMapDto.dirtySurfaceWaterPercent) &&
        Objects.equals(this.geoJson, waterMapDto.geoJson);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionId, regionName, dirtySurfaceWaterPercent, geoJson);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WaterMapDto {\n");
    sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n");
    sb.append("    regionName: ").append(toIndentedString(regionName)).append("\n");
    sb.append("    dirtySurfaceWaterPercent: ").append(toIndentedString(dirtySurfaceWaterPercent)).append("\n");
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

