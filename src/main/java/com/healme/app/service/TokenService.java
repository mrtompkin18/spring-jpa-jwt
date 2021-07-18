package com.healme.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.healme.app.common.config.ApplicationConfig;
import com.healme.app.common.constant.ApiConstant;
import com.healme.app.model.common.user.UserDetailModel;
import com.healme.app.repository.entity.PermissionEntity;
import com.healme.app.repository.entity.RoleEntity;
import com.healme.app.repository.entity.UserEntity;
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

    public String generateToken(UserEntity userEntity, Date expiredAt) {
        RoleEntity roleEntity = userEntity.getRoleEntity();
        List<String> permissionCode = new ArrayList<>();

        if (Objects.nonNull(roleEntity)) {
            permissionCode = roleEntity.getPermissionEntities()
                    .stream()
                    .map(PermissionEntity::getName)
                    .collect(Collectors.toList());
        }

        return JWT.create()
                .withIssuer(userEntity.getUsername())
                .withClaim(ApiConstant.CLAIM_KEY.USER_ID, userEntity.getUserId())
                .withClaim(ApiConstant.CLAIM_KEY.ROLE_ID, userEntity.getRoleEntity().getRoleId())
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
                .roleId(claims.get(ApiConstant.CLAIM_KEY.ROLE_ID).asLong())
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
