package com.example.eco_map.api.controller;

import com.example.eco_map.api.exception.ErrorResponse;
import com.example.eco_map.api.exception.RoleNotFoundException;
import com.example.eco_map.api.exception.UserAlreadyExistsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleValidationErrors(ConstraintViolationException ex) {
        log.error("Caught ConstraintViolationException", ex);
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors.toString());
    }

    private Mono<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        return Mono.just(new ErrorResponse(
                status.value(),
                message,
                ZonedDateTime.now().withZoneSameInstant(MOSCOW_ZONE)
        ));
    }
}
