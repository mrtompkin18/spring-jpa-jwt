package com.healme.app.common.config.jwt;

import com.healme.app.common.constant.ApiConstant;
import com.healme.app.model.common.user.UserDetailModel;
import com.healme.app.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtServletFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(ApiConstant.TOKEN_BEARER_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = StringUtils.trim(StringUtils.replace(authorizationHeader, ApiConstant.TOKEN_BEARER_TYPE, ApiConstant.SYMBOL.EMPTY));
        if (!this.tokenService.verifyToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetailModel userDetailModel = this.tokenService.getClaim(token);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailModel, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
