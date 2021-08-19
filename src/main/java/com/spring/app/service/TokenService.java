package com.spring.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.app.common.config.ApplicationConfig;
import com.spring.app.common.constant.ApiConstant;
import com.spring.app.model.common.user.UserDetailModel;
import com.spring.app.repository.entity.Permission;
import com.spring.app.repository.entity.Role;
import com.spring.app.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private ApplicationConfig applicationConfig;

    public String generateToken(User userEntity, Date expiredAt) {
        Role roleEntity = userEntity.getRole();

        List<String> permissionCode = Collections.emptyList();
        Long userId = userEntity.getUserId();
        Long roleId = null;

        if (Objects.nonNull(roleEntity)) {
            permissionCode = roleEntity.getPermissions()
                    .stream()
                    .map(Permission::getName)
                    .collect(Collectors.toList());
            roleId = roleEntity.getRoleId();
        }

        return JWT.create()
                .withIssuer(userEntity.getUsername())
                .withClaim(ApiConstant.CLAIM_KEY.USER_ID, userId)
                .withClaim(ApiConstant.CLAIM_KEY.PERMISSION_CODE, permissionCode)
                .withExpiresAt(expiredAt)
                .sign(this.getAlgorithm());
    }

    public UserDetailModel getClaim(String token) {
        JWTVerifier verifier = JWT.require(this.getAlgorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();

        return UserDetailModel.builder()
                .userId(claims.get(ApiConstant.CLAIM_KEY.USER_ID).asLong())
                .permissionsCode(claims.get(ApiConstant.CLAIM_KEY.PERMISSION_CODE).asList(String.class))
                .build();
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

    private Algorithm getAlgorithm() {
        String secretKey = this.applicationConfig.getJwtSecretKey();
        return Algorithm.HMAC256(secretKey);
    }
}
