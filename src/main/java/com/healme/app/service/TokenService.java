package com.healme.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.healme.app.common.config.ApplicationConfig;
import com.healme.app.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private ApplicationConfig applicationConfig;

    public String generateToken(User user, Date expiredAt) {
        return JWT.create()
                .withIssuer(user.getUsername())
                .withClaim("principal", user.getId())
                .withExpiresAt(expiredAt)
                .sign(this.getAlgorithm());
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("JWTVerificationException : {}", e.getMessage());
            return false;
        }
    }

    public Claim getClaim(String token) {
        JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("principal");
    }

    private Algorithm getAlgorithm() {
        String secretKey = this.applicationConfig.getJwtSecretKey();
        return Algorithm.HMAC256(secretKey);
    }
}
