package com.example.schoolspace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url:#{null}}")
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOrigin = (frontendUrl != null) ? frontendUrl : "http://localhost:3000";
        System.out.println("✅ CORS Config actif pour: " + allowedOrigin);
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000", "http://3.88.188.127:3000/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);;
    }
}