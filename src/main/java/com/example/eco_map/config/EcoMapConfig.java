package com.example.eco_map.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EcoMapConfig {
    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }

}
