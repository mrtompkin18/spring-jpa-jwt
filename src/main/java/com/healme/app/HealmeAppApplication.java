package com.healme.app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class HealmeAppApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HealmeAppApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
