package com.test.jwt.config;

import com.test.jwt.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Providers {

    @Bean
    public JwtService provideJwtService() {
        return new JwtService();
    }
}
