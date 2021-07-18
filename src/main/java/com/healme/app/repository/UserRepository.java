package com.healme.app.repository;

import com.healme.app.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);
}
