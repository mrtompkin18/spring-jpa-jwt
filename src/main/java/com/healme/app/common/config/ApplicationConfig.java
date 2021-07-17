package com.healme.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@Configuration
@EnableJpaAuditing
public class ApplicationConfig {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${jwt.expired-time}")
    private String jwtExpireTime;

}
