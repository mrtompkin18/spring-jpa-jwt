package com.healme.app.task.role;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.model.common.task.AbsGenericTask;
import com.healme.app.model.role.CreateRoleRequestModel;
import com.healme.app.repository.entity.RoleEntity;
import com.healme.app.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateRoleTask extends AbsGenericTask<CreateRoleRequestModel, ApiResponseModel> {

    @Autowired
    private RoleService roleService;

    @Override
    protected void validateBusiness(CreateRoleRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getRoleName())) {
            throw new ApiException(ErrorCode.REQUIRED, "Role name is required.");
        }
    }

    @Override
    protected ApiResponseModel processTask(CreateRoleRequestModel request) throws ApiException {
        RoleEntity roleEntity = this.roleService.create(request);
        log.info("create role success : {}", roleEntity);
        return new ApiResponseModel();
    }
}
