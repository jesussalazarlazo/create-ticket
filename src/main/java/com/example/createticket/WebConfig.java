package com.example.createticket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Ajusta esto según tu ruta
                .allowedOrigins("http://localhost:3000") // Cambia esto si tu frontend está en otro origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
