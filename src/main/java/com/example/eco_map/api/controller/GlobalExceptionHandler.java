package com.example.eco_map.api.controller;

import com.example.eco_map.api.exception.AirQualityNotFoundException;
import com.example.eco_map.api.exception.DuplicateFavoriteRegionException;
import com.example.eco_map.api.exception.ErrorResponse;
import com.example.eco_map.api.exception.RadiationNotFoundException;
import com.example.eco_map.api.exception.RegionNotFoundException;
import com.example.eco_map.api.exception.RoleNotFoundException;
import com.example.eco_map.api.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String EUROPE_MOSCOW = "Europe/Moscow";
    private static final ZoneId MOSCOW_ZONE = ZoneId.of(EUROPE_MOSCOW);


    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("Caught UserAlreadyExistsException", ex);
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        log.warn("Caught RoleNotFoundException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.warn("Caught BadCredentialsException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException ex) {
        log.warn("Validation failed: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Caught IllegalArgumentException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ErrorResponse> handleUnexpectedException(RuntimeException ex) {
        log.error("Unhandled RuntimeException", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
    }

    @ExceptionHandler(RegionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleRegionNotFoundException(RegionNotFoundException ex) {
        log.warn("Caught RegionNotFoundException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AirQualityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleAirQualityNotFoundException(AirQualityNotFoundException ex) {
        log.warn("Caught AirQualityNotFoundException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RadiationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleRadiationNotFoundException(RadiationNotFoundException ex) {
        log.warn("Caught RadiationNotFoundException: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateFavoriteRegionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ErrorResponse> handleDuplicateFavoriteRegionException(DuplicateFavoriteRegionException ex) {
        log.error("Caught DuplicateFavoriteRegionException", ex);
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    private Mono<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        return Mono.just(new ErrorResponse(
                status.value(),
                message,
                ZonedDateTime.now().withZoneSameInstant(MOSCOW_ZONE)
        ));
    }
}
