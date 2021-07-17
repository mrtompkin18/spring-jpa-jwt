package com.healme.app.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@UtilityClass
public class SecurityContextUtils {

    public Optional<String> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of((String) authentication.getPrincipal());
    }
}
