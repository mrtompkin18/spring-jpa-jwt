package com.healme.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class HealmeAppApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HealmeAppApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
