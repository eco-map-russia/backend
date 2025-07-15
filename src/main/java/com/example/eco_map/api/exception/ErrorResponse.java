package com.example.eco_map.api.exception;

import java.time.ZonedDateTime;

public record ErrorResponse(
        int statusCode,
        String message,
        ZonedDateTime timeStamp
) {
}

