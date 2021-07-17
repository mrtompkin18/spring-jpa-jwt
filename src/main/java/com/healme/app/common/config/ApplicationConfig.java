package com.healme.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationConfig {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${jwt.expired-time}")
    private String jwtExpireTime;

}
