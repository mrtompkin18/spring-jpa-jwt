package com.healme.app.controller;

import com.healme.app.common.annotation.LoggingController;
import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.user.UserRegisterRequestModel;
import com.healme.app.model.user.UserRegisterResponseModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @LoggingController
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public UserRegisterResponseModel login(@RequestBody UserRegisterRequestModel request) throws ApiException {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();

        if (StringUtils.isAnyBlank(username, password, email)) {
            throw new ApiException(ErrorConstant.USER_ERROR_CODE, "Username, Password or Email is null!");
        }

        try {
            User user = new User(username, password, email);
            User insertedUser = this.userService.create(user);
            return new UserRegisterResponseModel(insertedUser, "insert user successfully!");
        } catch (DataAccessException e) {
            throw new ApiException(ErrorConstant.DB_ERROR_CODE, e.getLocalizedMessage());
        }
    }

}
