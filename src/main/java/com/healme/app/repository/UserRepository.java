package com.healme.app.repository;

import com.healme.app.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
}
