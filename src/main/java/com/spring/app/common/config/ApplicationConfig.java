package com.spring.app.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@EnableSwagger2
@Configuration
@EnableCaching
@EnableJpaAuditing
@EnableWebSecurity
@EnableWebSocketMessageBroker
@EnableJpaRepositories(basePackages = "com.spring.app.repository")
public class ApplicationConfig {

    @Value("${jwt.secret-key:}")
    private String jwtSecretKey;

    @Value("${jwt.expired-time-in-second:}")
    private String jwtExpireTimeInSecond;
}
