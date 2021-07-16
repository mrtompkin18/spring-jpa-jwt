package com.healme.app.service;

import com.healme.app.repository.UserRepository;
import com.healme.app.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user) {
        return this.userRepository.save(user);
    }
}
