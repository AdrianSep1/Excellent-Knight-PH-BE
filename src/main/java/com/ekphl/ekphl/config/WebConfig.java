package com.ekphl.ekphl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for any endpoint starting with /api/
                .allowedOrigins("http://localhost:3000") // Frontend URL (change if needed)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
