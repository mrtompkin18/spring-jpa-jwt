package com.healme.app.controller;

import com.healme.app.common.annotation.RequiredPermissions;
import com.healme.app.common.constant.PermissionCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.login.LoginRequestModel;
import com.healme.app.model.login.LoginResponseModel;
import com.healme.app.model.user.UserProfileResponseModel;
import com.healme.app.model.user.UserRegisterRequestModel;
import com.healme.app.model.user.UserRegisterResponseModel;
import com.healme.app.task.LoginTask;
import com.healme.app.task.SingUpTask;
import com.healme.app.task.UserProfileTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SingUpTask singUpTask;

    @Autowired
    private LoginTask loginTask;

    @Autowired
    private UserProfileTask userProfileTask;

    @PostMapping("/signup")
    public UserRegisterResponseModel signup(@RequestBody UserRegisterRequestModel request) throws ApiException {
        return this.singUpTask.executeTask(request);
    }

    @PostMapping("/login")
    public LoginResponseModel login(@RequestBody LoginRequestModel request) throws ApiException {
        return this.loginTask.executeTask(request);
    }

    @GetMapping("/me")
    @RequiredPermissions(requiredAll = false, groups = PermissionCode.USR010500)
    public UserProfileResponseModel profile() throws ApiException {
        return this.userProfileTask.executeTask(null);
    }
}
