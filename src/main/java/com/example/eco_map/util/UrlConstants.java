package com.example.eco_map.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UrlConstants {
    public static final String CURRENT_AIR_QUALITY_URL = "http://air-quality-api.open-meteo.com/v1/air-quality?latitude=%s&longitude=%s&current=pm10,pm2_5";
}
