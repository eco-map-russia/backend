package com.example.eco_map.usecases.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RadiationDataDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-29T11:29:36.357215400+03:00[Europe/Moscow]", comments = "Generator version: 7.14.0")
public class RadiationDataDto {

  private @Nullable BigDecimal betaFallout;

  public RadiationDataDto betaFallout(@Nullable BigDecimal betaFallout) {
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
    RadiationDataDto radiationDataDto = (RadiationDataDto) o;
    return Objects.equals(this.betaFallout, radiationDataDto.betaFallout);
  }

  @Override
  public int hashCode() {
    return Objects.hash(betaFallout);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RadiationDataDto {\n");
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

