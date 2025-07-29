package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.example.eco_map.usecases.dto.CoordinatesResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AirMapDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class AirMapDto implements GetMapLayerData200ResponseInner {

  private @Nullable UUID pointId;

  private @Nullable String pointName;

  private @Nullable CoordinatesResponseDto coordinatesResponseDto;

  private @Nullable BigDecimal pm25;

  private @Nullable BigDecimal pm10;

  private @Nullable BigDecimal carbonMonoxide;

  private @Nullable BigDecimal carbonDioxide;

  private @Nullable BigDecimal nitrogenDioxide;

  private @Nullable BigDecimal sulphurDioxide;

  private @Nullable BigDecimal ozone;

  private @Nullable BigDecimal aerosolOpticalDepth;

  private @Nullable BigDecimal dust;

  private @Nullable BigDecimal methane;

  private @Nullable BigDecimal europeanAqi;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime time;

  public AirMapDto pointId(@Nullable UUID pointId) {
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

  public AirMapDto pointName(@Nullable String pointName) {
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

  public AirMapDto coordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
    this.coordinatesResponseDto = coordinatesResponseDto;
    return this;
  }

  /**
   * Get coordinatesResponseDto
   * @return coordinatesResponseDto
   */
  @Valid 
  @Schema(name = "CoordinatesResponseDto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("CoordinatesResponseDto")
  public @Nullable CoordinatesResponseDto getCoordinatesResponseDto() {
    return coordinatesResponseDto;
  }

  public void setCoordinatesResponseDto(@Nullable CoordinatesResponseDto coordinatesResponseDto) {
    this.coordinatesResponseDto = coordinatesResponseDto;
  }

  public AirMapDto pm25(@Nullable BigDecimal pm25) {
    this.pm25 = pm25;
    return this;
  }

  /**
   * Get pm25
   * @return pm25
   */
  @Valid 
  @Schema(name = "pm25", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pm25")
  public @Nullable BigDecimal getPm25() {
    return pm25;
  }

  public void setPm25(@Nullable BigDecimal pm25) {
    this.pm25 = pm25;
  }

  public AirMapDto pm10(@Nullable BigDecimal pm10) {
    this.pm10 = pm10;
    return this;
  }

  /**
   * Get pm10
   * @return pm10
   */
  @Valid 
  @Schema(name = "pm10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pm10")
  public @Nullable BigDecimal getPm10() {
    return pm10;
  }

  public void setPm10(@Nullable BigDecimal pm10) {
    this.pm10 = pm10;
  }

  public AirMapDto carbonMonoxide(@Nullable BigDecimal carbonMonoxide) {
    this.carbonMonoxide = carbonMonoxide;
    return this;
  }

  /**
   * Get carbonMonoxide
   * @return carbonMonoxide
   */
  @Valid 
  @Schema(name = "carbonMonoxide", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("carbonMonoxide")
  public @Nullable BigDecimal getCarbonMonoxide() {
    return carbonMonoxide;
  }

  public void setCarbonMonoxide(@Nullable BigDecimal carbonMonoxide) {
    this.carbonMonoxide = carbonMonoxide;
  }

  public AirMapDto carbonDioxide(@Nullable BigDecimal carbonDioxide) {
    this.carbonDioxide = carbonDioxide;
    return this;
  }

  /**
   * Get carbonDioxide
   * @return carbonDioxide
   */
  @Valid 
  @Schema(name = "carbonDioxide", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("carbonDioxide")
  public @Nullable BigDecimal getCarbonDioxide() {
    return carbonDioxide;
  }

  public void setCarbonDioxide(@Nullable BigDecimal carbonDioxide) {
    this.carbonDioxide = carbonDioxide;
  }

  public AirMapDto nitrogenDioxide(@Nullable BigDecimal nitrogenDioxide) {
    this.nitrogenDioxide = nitrogenDioxide;
    return this;
  }

  /**
   * Get nitrogenDioxide
   * @return nitrogenDioxide
   */
  @Valid 
  @Schema(name = "nitrogenDioxide", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nitrogenDioxide")
  public @Nullable BigDecimal getNitrogenDioxide() {
    return nitrogenDioxide;
  }

  public void setNitrogenDioxide(@Nullable BigDecimal nitrogenDioxide) {
    this.nitrogenDioxide = nitrogenDioxide;
  }

  public AirMapDto sulphurDioxide(@Nullable BigDecimal sulphurDioxide) {
    this.sulphurDioxide = sulphurDioxide;
    return this;
  }

  /**
   * Get sulphurDioxide
   * @return sulphurDioxide
   */
  @Valid 
  @Schema(name = "sulphurDioxide", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sulphurDioxide")
  public @Nullable BigDecimal getSulphurDioxide() {
    return sulphurDioxide;
  }

  public void setSulphurDioxide(@Nullable BigDecimal sulphurDioxide) {
    this.sulphurDioxide = sulphurDioxide;
  }

  public AirMapDto ozone(@Nullable BigDecimal ozone) {
    this.ozone = ozone;
    return this;
  }

  /**
   * Get ozone
   * @return ozone
   */
  @Valid 
  @Schema(name = "ozone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ozone")
  public @Nullable BigDecimal getOzone() {
    return ozone;
  }

  public void setOzone(@Nullable BigDecimal ozone) {
    this.ozone = ozone;
  }

  public AirMapDto aerosolOpticalDepth(@Nullable BigDecimal aerosolOpticalDepth) {
    this.aerosolOpticalDepth = aerosolOpticalDepth;
    return this;
  }

  /**
   * Get aerosolOpticalDepth
   * @return aerosolOpticalDepth
   */
  @Valid 
  @Schema(name = "aerosolOpticalDepth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("aerosolOpticalDepth")
  public @Nullable BigDecimal getAerosolOpticalDepth() {
    return aerosolOpticalDepth;
  }

  public void setAerosolOpticalDepth(@Nullable BigDecimal aerosolOpticalDepth) {
    this.aerosolOpticalDepth = aerosolOpticalDepth;
  }

  public AirMapDto dust(@Nullable BigDecimal dust) {
    this.dust = dust;
    return this;
  }

  /**
   * Get dust
   * @return dust
   */
  @Valid 
  @Schema(name = "dust", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dust")
  public @Nullable BigDecimal getDust() {
    return dust;
  }

  public void setDust(@Nullable BigDecimal dust) {
    this.dust = dust;
  }

  public AirMapDto methane(@Nullable BigDecimal methane) {
    this.methane = methane;
    return this;
  }

  /**
   * Get methane
   * @return methane
   */
  @Valid 
  @Schema(name = "methane", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("methane")
  public @Nullable BigDecimal getMethane() {
    return methane;
  }

  public void setMethane(@Nullable BigDecimal methane) {
    this.methane = methane;
  }

  public AirMapDto europeanAqi(@Nullable BigDecimal europeanAqi) {
    this.europeanAqi = europeanAqi;
    return this;
  }

  /**
   * Get europeanAqi
   * @return europeanAqi
   */
  @Valid 
  @Schema(name = "europeanAqi", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("europeanAqi")
  public @Nullable BigDecimal getEuropeanAqi() {
    return europeanAqi;
  }

  public void setEuropeanAqi(@Nullable BigDecimal europeanAqi) {
    this.europeanAqi = europeanAqi;
  }

  public AirMapDto time(@Nullable OffsetDateTime time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
   */
  @Valid 
  @Schema(name = "time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("time")
  public @Nullable OffsetDateTime getTime() {
    return time;
  }

  public void setTime(@Nullable OffsetDateTime time) {
    this.time = time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AirMapDto airMapDto = (AirMapDto) o;
    return Objects.equals(this.pointId, airMapDto.pointId) &&
        Objects.equals(this.pointName, airMapDto.pointName) &&
        Objects.equals(this.coordinatesResponseDto, airMapDto.coordinatesResponseDto) &&
        Objects.equals(this.pm25, airMapDto.pm25) &&
        Objects.equals(this.pm10, airMapDto.pm10) &&
        Objects.equals(this.carbonMonoxide, airMapDto.carbonMonoxide) &&
        Objects.equals(this.carbonDioxide, airMapDto.carbonDioxide) &&
        Objects.equals(this.nitrogenDioxide, airMapDto.nitrogenDioxide) &&
        Objects.equals(this.sulphurDioxide, airMapDto.sulphurDioxide) &&
        Objects.equals(this.ozone, airMapDto.ozone) &&
        Objects.equals(this.aerosolOpticalDepth, airMapDto.aerosolOpticalDepth) &&
        Objects.equals(this.dust, airMapDto.dust) &&
        Objects.equals(this.methane, airMapDto.methane) &&
        Objects.equals(this.europeanAqi, airMapDto.europeanAqi) &&
        Objects.equals(this.time, airMapDto.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointId, pointName, coordinatesResponseDto, pm25, pm10, carbonMonoxide, carbonDioxide, nitrogenDioxide, sulphurDioxide, ozone, aerosolOpticalDepth, dust, methane, europeanAqi, time);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AirMapDto {\n");
    sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
    sb.append("    pointName: ").append(toIndentedString(pointName)).append("\n");
    sb.append("    coordinatesResponseDto: ").append(toIndentedString(coordinatesResponseDto)).append("\n");
    sb.append("    pm25: ").append(toIndentedString(pm25)).append("\n");
    sb.append("    pm10: ").append(toIndentedString(pm10)).append("\n");
    sb.append("    carbonMonoxide: ").append(toIndentedString(carbonMonoxide)).append("\n");
    sb.append("    carbonDioxide: ").append(toIndentedString(carbonDioxide)).append("\n");
    sb.append("    nitrogenDioxide: ").append(toIndentedString(nitrogenDioxide)).append("\n");
    sb.append("    sulphurDioxide: ").append(toIndentedString(sulphurDioxide)).append("\n");
    sb.append("    ozone: ").append(toIndentedString(ozone)).append("\n");
    sb.append("    aerosolOpticalDepth: ").append(toIndentedString(aerosolOpticalDepth)).append("\n");
    sb.append("    dust: ").append(toIndentedString(dust)).append("\n");
    sb.append("    methane: ").append(toIndentedString(methane)).append("\n");
    sb.append("    europeanAqi: ").append(toIndentedString(europeanAqi)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
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

