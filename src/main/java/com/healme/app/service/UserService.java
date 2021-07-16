package com.healme.app.service;

import com.healme.app.repository.UserRepository;
import com.healme.app.repository.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(User user) {
        String common = String.valueOf(System.currentTimeMillis());
        this.userRepository.save(new User(common, "root", common));
        this.userRepository.save(user);
    }
}
