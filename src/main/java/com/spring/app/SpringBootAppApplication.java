package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@EnableJpaRepositories(basePackages = "com.spring.app.repository")
@SpringBootApplication
public class SpringBootAppApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        SpringApplication.run(SpringBootAppApplication.class, args);
    }
}
