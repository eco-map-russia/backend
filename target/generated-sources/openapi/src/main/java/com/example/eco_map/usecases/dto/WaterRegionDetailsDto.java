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
 * WaterRegionDetailsDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class WaterRegionDetailsDto {

  private @Nullable Double dirtySurfaceWaterPercent;

  public WaterRegionDetailsDto dirtySurfaceWaterPercent(@Nullable Double dirtySurfaceWaterPercent) {
    this.dirtySurfaceWaterPercent = dirtySurfaceWaterPercent;
    return this;
  }

  /**
   * Get dirtySurfaceWaterPercent
   * @return dirtySurfaceWaterPercent
   */
  
  @Schema(name = "dirtySurfaceWaterPercent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dirtySurfaceWaterPercent")
  public @Nullable Double getDirtySurfaceWaterPercent() {
    return dirtySurfaceWaterPercent;
  }

  public void setDirtySurfaceWaterPercent(@Nullable Double dirtySurfaceWaterPercent) {
    this.dirtySurfaceWaterPercent = dirtySurfaceWaterPercent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WaterRegionDetailsDto waterRegionDetailsDto = (WaterRegionDetailsDto) o;
    return Objects.equals(this.dirtySurfaceWaterPercent, waterRegionDetailsDto.dirtySurfaceWaterPercent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dirtySurfaceWaterPercent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WaterRegionDetailsDto {\n");
    sb.append("    dirtySurfaceWaterPercent: ").append(toIndentedString(dirtySurfaceWaterPercent)).append("\n");
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

