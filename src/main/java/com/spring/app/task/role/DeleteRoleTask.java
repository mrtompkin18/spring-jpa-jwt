package com.spring.app.task.role;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.role.CRUDRoleRequestModel;
import com.spring.app.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class DeleteRoleTask extends AbsGenericTask<CRUDRoleRequestModel, ApiResponseModel<?>> {

    @Autowired
    private RoleService roleService;

    @Override
    protected void validateBusiness(CRUDRoleRequestModel request) throws ApiException {
        if (Objects.isNull(request.getRoleId())) {
            throw new ApiException(ErrorCode.REQUIRED, "Role id is required.");
        }
    }

    @Override
    protected ApiResponseModel<?> processTask(CRUDRoleRequestModel request) throws ApiException {
        this.roleService.deleteRoleById(request.getRoleId());
        return new ApiResponseModel<>();
    }
}
