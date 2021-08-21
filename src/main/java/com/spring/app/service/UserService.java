package com.spring.app.service;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.repository.UserRepository;
import com.spring.app.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        return this.userRepository.save(user);
    }

    public User findByUsername(String username) throws ApiException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Username does not exist."));
    }

    public Optional<User> safeFindByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User findById(Long id) throws ApiException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "User not found."));
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsernameIgnoreCase(username);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean isPasswordMatched(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
