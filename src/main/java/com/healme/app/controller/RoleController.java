package com.healme.app.controller;

import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.model.role.CreateRoleRequestModel;
import com.healme.app.task.role.CreateRoleTask;
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
