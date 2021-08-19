package com.spring.app.controller;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.role.CreateRoleRequestModel;
import com.spring.app.task.role.CreateRoleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private CreateRoleTask createRoleTask;

    @PostMapping("/create")
    private ApiResponseModel createRole(@RequestBody CreateRoleRequestModel request) throws ApiException {
        return this.createRoleTask.executeTask(request);
    }
}
