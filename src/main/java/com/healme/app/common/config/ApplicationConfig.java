package com.healme.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Data
@Configuration
@EnableJpaAuditing
@EnableWebSecurity
public class ApplicationConfig {
    
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${jwt.expired-time}")
    private String jwtExpireTime;
}
