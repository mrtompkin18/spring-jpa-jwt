package com.spring.app.task.role;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.pagination.PaginationModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InquiryRoleTask extends AbsGenericTask<InquiryRoleRequestModel, ApiResponseModel<List<Role>>> {

    @Autowired
    private RoleService roleService;

    @Override
    protected ApiResponseModel<List<Role>> processTask(InquiryRoleRequestModel request) throws ApiException {
        PaginationModel<Role> result = this.roleService.inquiryByCriteria(request);
        return this.getPagingResponse(result);
    }
}
