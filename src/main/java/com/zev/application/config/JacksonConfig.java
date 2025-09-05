package com.zev.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper m = new ObjectMapper();
        m.registerModule(new JavaTimeModule());
        m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        m.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        m.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        m.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
        return m;
    }
}
