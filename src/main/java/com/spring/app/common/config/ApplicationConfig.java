package com.spring.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Data
@Configuration
@EnableCaching
@EnableJpaAuditing
@EnableWebSecurity
public class ApplicationConfig {

    @Value("${jwt.secret-key:}")
    private String jwtSecretKey;

    @Value("${jwt.expired-time-in-second:}")
    private String jwtExpireTimeInSecond;
}
