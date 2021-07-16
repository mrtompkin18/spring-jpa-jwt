package com.healme.app.controller;

import com.healme.app.common.annotation.LoggingController;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.user.UserRegisterRequestModel;
import com.healme.app.model.user.UserRegisterResponseModel;
import com.healme.app.task.UserRegisterTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegisterTask userRegisterTask;

    @LoggingController
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public UserRegisterResponseModel signup(@RequestBody UserRegisterRequestModel request) throws ApiException {
        return this.userRegisterTask.processTask(request);
    }
}
