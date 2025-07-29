package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.example.eco_map.usecases.dto.SoilRegionDetailsDto;
import com.example.eco_map.usecases.dto.WaterRegionDetailsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * RegionDetailsDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class RegionDetailsDto {

  private @Nullable UUID regionId;

  private @Nullable String name;

  private @Nullable CoordinatesResponseDto center;

  private @Nullable SoilRegionDetailsDto soilData;

  private @Nullable WaterRegionDetailsDto waterData;

  public RegionDetailsDto regionId(@Nullable UUID regionId) {
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

  public RegionDetailsDto name(@Nullable String name) {
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

  public RegionDetailsDto center(@Nullable CoordinatesResponseDto center) {
    this.center = center;
    return this;
  }

  /**
   * Get center
   * @return center
   */
  @Valid 
  @Schema(name = "center", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("center")
  public @Nullable CoordinatesResponseDto getCenter() {
    return center;
  }

  public void setCenter(@Nullable CoordinatesResponseDto center) {
    this.center = center;
  }

  public RegionDetailsDto soilData(@Nullable SoilRegionDetailsDto soilData) {
    this.soilData = soilData;
    return this;
  }

  /**
   * Get soilData
   * @return soilData
   */
  @Valid 
  @Schema(name = "soilData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("soilData")
  public @Nullable SoilRegionDetailsDto getSoilData() {
    return soilData;
  }

  public void setSoilData(@Nullable SoilRegionDetailsDto soilData) {
    this.soilData = soilData;
  }

  public RegionDetailsDto waterData(@Nullable WaterRegionDetailsDto waterData) {
    this.waterData = waterData;
    return this;
  }

  /**
   * Get waterData
   * @return waterData
   */
  @Valid 
  @Schema(name = "waterData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("waterData")
  public @Nullable WaterRegionDetailsDto getWaterData() {
    return waterData;
  }

  public void setWaterData(@Nullable WaterRegionDetailsDto waterData) {
    this.waterData = waterData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegionDetailsDto regionDetailsDto = (RegionDetailsDto) o;
    return Objects.equals(this.regionId, regionDetailsDto.regionId) &&
        Objects.equals(this.name, regionDetailsDto.name) &&
        Objects.equals(this.center, regionDetailsDto.center) &&
        Objects.equals(this.soilData, regionDetailsDto.soilData) &&
        Objects.equals(this.waterData, regionDetailsDto.waterData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionId, name, center, soilData, waterData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegionDetailsDto {\n");
    sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    center: ").append(toIndentedString(center)).append("\n");
    sb.append("    soilData: ").append(toIndentedString(soilData)).append("\n");
    sb.append("    waterData: ").append(toIndentedString(waterData)).append("\n");
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

