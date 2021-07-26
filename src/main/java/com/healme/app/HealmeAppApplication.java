package com.healme.app;

import com.healme.app.repository.UserRepository;
import com.healme.app.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class HealmeAppApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        SpringApplication.run(HealmeAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (arg) -> {
            Optional<UserEntity> userEntity = userRepository.findById(1L);

            if (userEntity.isEmpty()) {
                UserEntity createUser = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@admin.com")
                        .build();

                UserEntity newUser = userRepository.save(createUser);
                log.info("create new user : {}", newUser);
            }
        };
    }
}
