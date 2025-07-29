package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AirQualityResponseDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class AirQualityResponseDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime time;

  private @Nullable BigDecimal pm10;

  private @Nullable BigDecimal pm25;

  private @Nullable BigDecimal carbonMonoxide;

  private @Nullable BigDecimal carbonDioxide;

  private @Nullable BigDecimal nitrogenDioxide;

  private @Nullable BigDecimal sulphurDioxide;

  private @Nullable BigDecimal ozone;

  private @Nullable BigDecimal aerosolOpticalDepth;

  private @Nullable BigDecimal dust;

  private @Nullable BigDecimal methane;

  private @Nullable BigDecimal europeanAqi;

  public AirQualityResponseDto time(@Nullable OffsetDateTime time) {
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

  public AirQualityResponseDto pm10(@Nullable BigDecimal pm10) {
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

  public AirQualityResponseDto pm25(@Nullable BigDecimal pm25) {
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

  public AirQualityResponseDto carbonMonoxide(@Nullable BigDecimal carbonMonoxide) {
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

  public AirQualityResponseDto carbonDioxide(@Nullable BigDecimal carbonDioxide) {
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

  public AirQualityResponseDto nitrogenDioxide(@Nullable BigDecimal nitrogenDioxide) {
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

  public AirQualityResponseDto sulphurDioxide(@Nullable BigDecimal sulphurDioxide) {
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

  public AirQualityResponseDto ozone(@Nullable BigDecimal ozone) {
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

  public AirQualityResponseDto aerosolOpticalDepth(@Nullable BigDecimal aerosolOpticalDepth) {
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

  public AirQualityResponseDto dust(@Nullable BigDecimal dust) {
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

  public AirQualityResponseDto methane(@Nullable BigDecimal methane) {
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

  public AirQualityResponseDto europeanAqi(@Nullable BigDecimal europeanAqi) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AirQualityResponseDto airQualityResponseDto = (AirQualityResponseDto) o;
    return Objects.equals(this.time, airQualityResponseDto.time) &&
        Objects.equals(this.pm10, airQualityResponseDto.pm10) &&
        Objects.equals(this.pm25, airQualityResponseDto.pm25) &&
        Objects.equals(this.carbonMonoxide, airQualityResponseDto.carbonMonoxide) &&
        Objects.equals(this.carbonDioxide, airQualityResponseDto.carbonDioxide) &&
        Objects.equals(this.nitrogenDioxide, airQualityResponseDto.nitrogenDioxide) &&
        Objects.equals(this.sulphurDioxide, airQualityResponseDto.sulphurDioxide) &&
        Objects.equals(this.ozone, airQualityResponseDto.ozone) &&
        Objects.equals(this.aerosolOpticalDepth, airQualityResponseDto.aerosolOpticalDepth) &&
        Objects.equals(this.dust, airQualityResponseDto.dust) &&
        Objects.equals(this.methane, airQualityResponseDto.methane) &&
        Objects.equals(this.europeanAqi, airQualityResponseDto.europeanAqi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, pm10, pm25, carbonMonoxide, carbonDioxide, nitrogenDioxide, sulphurDioxide, ozone, aerosolOpticalDepth, dust, methane, europeanAqi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AirQualityResponseDto {\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    pm10: ").append(toIndentedString(pm10)).append("\n");
    sb.append("    pm25: ").append(toIndentedString(pm25)).append("\n");
    sb.append("    carbonMonoxide: ").append(toIndentedString(carbonMonoxide)).append("\n");
    sb.append("    carbonDioxide: ").append(toIndentedString(carbonDioxide)).append("\n");
    sb.append("    nitrogenDioxide: ").append(toIndentedString(nitrogenDioxide)).append("\n");
    sb.append("    sulphurDioxide: ").append(toIndentedString(sulphurDioxide)).append("\n");
    sb.append("    ozone: ").append(toIndentedString(ozone)).append("\n");
    sb.append("    aerosolOpticalDepth: ").append(toIndentedString(aerosolOpticalDepth)).append("\n");
    sb.append("    dust: ").append(toIndentedString(dust)).append("\n");
    sb.append("    methane: ").append(toIndentedString(methane)).append("\n");
    sb.append("    europeanAqi: ").append(toIndentedString(europeanAqi)).append("\n");
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

