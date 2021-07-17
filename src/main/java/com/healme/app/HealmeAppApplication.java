package com.healme.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.TimeZone;

@EnableWebSecurity
@SpringBootApplication
public class HealmeAppApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        SpringApplication.run(HealmeAppApplication.class, args);
    }
}
