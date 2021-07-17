package com.healme.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.healme.app.common.config.ApplicationConfig;
import com.healme.app.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private ApplicationConfig applicationConfig;

    public String generateToken(User user) {
        String secretKey = this.applicationConfig.getAppSecretKey();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        return JWT.create()
                .withIssuer(user.getUsername())
                .withClaim("principal", user.getId())
                .sign(algorithm);
    }
}
