package com.healme.app;

import com.healme.app.common.constant.ApiConstant;
import com.healme.app.common.constant.PermissionCode;
import com.healme.app.repository.RoleRepository;
import com.healme.app.repository.UserRepository;
import com.healme.app.repository.entity.PermissionEntity;
import com.healme.app.repository.entity.RoleEntity;
import com.healme.app.repository.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.TimeZone;

@SpringBootApplication
public class HealmeAppApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
        SpringApplication.run(HealmeAppApplication.class, args);
    }

    // @Bean
    @Transactional
    public CommandLineRunner runner(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            PermissionEntity permissionEntity = PermissionEntity.builder()
                    .flag(ApiConstant.FLAG.A.name())
                    .name(PermissionCode.USR010100.name())
                    .description("User search")
                    .build();

            RoleEntity roleEntity = RoleEntity.builder()
                    .flag(ApiConstant.FLAG.A.name())
                    .name("Administrator")
                    .permissionEntities(Collections.singletonList(permissionEntity))
                    .build();

            RoleEntity insert = roleRepository.save(roleEntity);

            UserEntity userEntity = UserEntity.builder()
                    .email("admin@admin.com")
                    .username("admin")
                    .password(passwordEncoder.encode("root"))
                    .roleEntity(insert)
                    .build();

            userRepository.save(userEntity);
        };
    }
}
