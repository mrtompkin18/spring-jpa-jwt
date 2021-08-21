package com.spring.app.controller;

import com.spring.app.common.annotation.RequiredPermissions;
import com.spring.app.common.constant.PermissionCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.user.UserDetailModel;
import com.spring.app.model.login.LoginRequestModel;
import com.spring.app.model.login.LoginResponseModel;
import com.spring.app.model.user.UserProfileRequestModel;
import com.spring.app.model.user.UserRegisterRequestModel;
import com.spring.app.repository.entity.User;
import com.spring.app.task.auth.LoginTask;
import com.spring.app.task.auth.SingUpTask;
import com.spring.app.task.user.UserProfileTask;
import com.spring.app.util.SecurityUtils;
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
    public ApiResponseModel<User> signup(@RequestBody UserRegisterRequestModel request) throws ApiException {
        return this.singUpTask.executeTask(request);
    }

    @PostMapping("/login")
    public ApiResponseModel<LoginResponseModel> login(@RequestBody LoginRequestModel request) throws ApiException {
        return this.loginTask.executeTask(request);
    }

    @GetMapping("/me")
    @RequiredPermissions(
            requiredAll = false,
            groups = {PermissionCode.USR010100, PermissionCode.USR010200, PermissionCode.USR010300, PermissionCode.USR010400, PermissionCode.USR010500}
    )
    public ApiResponseModel<User> profile() throws ApiException {
        UserDetailModel userDetailModel = SecurityUtils.getUserDetail();
        Long userId = userDetailModel.getUserId();

        UserProfileRequestModel request = UserProfileRequestModel.builder().userId(userId).build();
        return this.userProfileTask.executeTask(request);
    }
}
