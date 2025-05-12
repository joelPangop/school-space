package com.example.schoolspace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);   // log les paramètres URL
        filter.setIncludePayload(true);       // log le body JSON
        filter.setMaxPayloadLength(10000);    // taille maximale du body à logger
        filter.setIncludeHeaders(false);      // headers facultatifs
        return filter;
    }
}
