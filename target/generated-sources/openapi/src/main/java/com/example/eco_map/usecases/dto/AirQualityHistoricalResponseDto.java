package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.LocalDate;
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
 * AirQualityHistoricalResponseDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class AirQualityHistoricalResponseDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate date;

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

  public AirQualityHistoricalResponseDto date(@Nullable LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public @Nullable LocalDate getDate() {
    return date;
  }

  public void setDate(@Nullable LocalDate date) {
    this.date = date;
  }

  public AirQualityHistoricalResponseDto pm10(@Nullable BigDecimal pm10) {
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

  public AirQualityHistoricalResponseDto pm25(@Nullable BigDecimal pm25) {
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

  public AirQualityHistoricalResponseDto carbonMonoxide(@Nullable BigDecimal carbonMonoxide) {
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

  public AirQualityHistoricalResponseDto carbonDioxide(@Nullable BigDecimal carbonDioxide) {
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

  public AirQualityHistoricalResponseDto nitrogenDioxide(@Nullable BigDecimal nitrogenDioxide) {
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

  public AirQualityHistoricalResponseDto sulphurDioxide(@Nullable BigDecimal sulphurDioxide) {
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

  public AirQualityHistoricalResponseDto ozone(@Nullable BigDecimal ozone) {
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

  public AirQualityHistoricalResponseDto aerosolOpticalDepth(@Nullable BigDecimal aerosolOpticalDepth) {
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

  public AirQualityHistoricalResponseDto dust(@Nullable BigDecimal dust) {
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

  public AirQualityHistoricalResponseDto methane(@Nullable BigDecimal methane) {
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

  public AirQualityHistoricalResponseDto europeanAqi(@Nullable BigDecimal europeanAqi) {
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
    AirQualityHistoricalResponseDto airQualityHistoricalResponseDto = (AirQualityHistoricalResponseDto) o;
    return Objects.equals(this.date, airQualityHistoricalResponseDto.date) &&
        Objects.equals(this.pm10, airQualityHistoricalResponseDto.pm10) &&
        Objects.equals(this.pm25, airQualityHistoricalResponseDto.pm25) &&
        Objects.equals(this.carbonMonoxide, airQualityHistoricalResponseDto.carbonMonoxide) &&
        Objects.equals(this.carbonDioxide, airQualityHistoricalResponseDto.carbonDioxide) &&
        Objects.equals(this.nitrogenDioxide, airQualityHistoricalResponseDto.nitrogenDioxide) &&
        Objects.equals(this.sulphurDioxide, airQualityHistoricalResponseDto.sulphurDioxide) &&
        Objects.equals(this.ozone, airQualityHistoricalResponseDto.ozone) &&
        Objects.equals(this.aerosolOpticalDepth, airQualityHistoricalResponseDto.aerosolOpticalDepth) &&
        Objects.equals(this.dust, airQualityHistoricalResponseDto.dust) &&
        Objects.equals(this.methane, airQualityHistoricalResponseDto.methane) &&
        Objects.equals(this.europeanAqi, airQualityHistoricalResponseDto.europeanAqi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, pm10, pm25, carbonMonoxide, carbonDioxide, nitrogenDioxide, sulphurDioxide, ozone, aerosolOpticalDepth, dust, methane, europeanAqi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AirQualityHistoricalResponseDto {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

