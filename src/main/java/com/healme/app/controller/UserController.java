package com.healme.app.controller;

import com.healme.app.common.annotation.HealmeControllerHandler;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.login.LoginRequestModel;
import com.healme.app.model.login.LoginResponseModel;
import com.healme.app.model.user.UserRegisterRequestModel;
import com.healme.app.model.user.UserRegisterResponseModel;
import com.healme.app.task.LoginTask;
import com.healme.app.task.SingUpTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SingUpTask singUpTask;

    @Autowired
    private LoginTask loginTask;

    @HealmeControllerHandler
    @PostMapping("/signup")
    public UserRegisterResponseModel signup(@RequestBody UserRegisterRequestModel request) throws ApiException {
        return this.singUpTask.executeTask(request);
    }

    @HealmeControllerHandler
    @PostMapping("/login")
    public LoginResponseModel login(@RequestBody LoginRequestModel request) throws ApiException {
        return this.loginTask.executeTask(request);
    }

}
