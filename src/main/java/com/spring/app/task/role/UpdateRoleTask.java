package com.spring.app.task.role;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.role.CRUDRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class UpdateRoleTask extends AbsGenericTask<CRUDRoleRequestModel, ApiResponseModel<Role>> {

    @Autowired
    private RoleService roleService;

    @Override
    protected void validateBusiness(CRUDRoleRequestModel request) throws ApiException {
        if (Objects.isNull(request.getRoleId())) {
            throw new ApiException(ErrorCode.REQUIRED, "Role id is required.");
        } else if (StringUtils.isBlank(request.getRoleName())) {
            throw new ApiException(ErrorCode.REQUIRED, "Role name is required.");
        }
    }

    @Override
    protected ApiResponseModel<Role> processTask(CRUDRoleRequestModel request) throws ApiException {
        Role role = this.roleService.updateRole(request);
        return new ApiResponseModel<>(role);
    }
}
