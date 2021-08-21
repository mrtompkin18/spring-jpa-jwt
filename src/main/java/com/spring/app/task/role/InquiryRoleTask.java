package com.spring.app.task.role;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.pagination.PageRequestModel;
import com.spring.app.model.common.pagination.PageResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InquiryRoleTask extends AbsGenericTask<InquiryRoleRequestModel, ApiResponseModel<PageResponseModel<Role>>> {

    @Autowired
    private RoleService roleService;

    @Override
    protected ApiResponseModel<PageResponseModel<Role>> processTask(InquiryRoleRequestModel request) throws ApiException {
        String roleName = request.getRoleName();
        String flag = request.getFlag();
        PageRequestModel paging = request.getPaging();

        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize());
        PageResponseModel<Role> result = this.roleService.findByCriteria(roleName, flag, pageable);

        return new ApiResponseModel<>(result);
    }
}
