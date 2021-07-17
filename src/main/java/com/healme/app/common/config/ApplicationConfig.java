package com.healme.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationConfig {

    @Value("${app.secret-key}")
    private String appSecretKey;
}
