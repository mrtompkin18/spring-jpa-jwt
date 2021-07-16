package com.healme.app.controller;

import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.model.login.LoginRequestModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/login")

    public ResponseEntity<ApiResponseModel> login(@RequestBody LoginRequestModel request) throws ApiException {
        String username = request.getUsername();
        String password = request.getPassword();

        if (StringUtils.isAnyBlank(username, password)) {
            throw new ApiException(ErrorConstant.USER_ERROR_CODE, "Username or Password is required.");
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(System.currentTimeMillis() + "@gmail.com");
            this.userService.create(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(ErrorConstant.DB_ERROR_CODE, e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

}
