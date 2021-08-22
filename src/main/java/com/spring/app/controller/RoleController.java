package com.spring.app.controller;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.pagination.PageResponseModel;
import com.spring.app.model.role.CRUDRoleRequestModel;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.task.role.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private CreateRoleTask createRoleTask;

    @Autowired
    private UpdateRoleTask updateRoleTask;

    @Autowired
    private DeleteRoleTask deleteRoleTask;

    @Autowired
    private FindRoleTask findRoleTask;

    @Autowired
    private InquiryRoleTask inquiryRoleTask;

    @PostMapping
    public ApiResponseModel<PageResponseModel<Role>> inquiryRole(@RequestBody InquiryRoleRequestModel request) throws ApiException {
        return this.inquiryRoleTask.executeTask(request);
    }

    @Cacheable(value = "role", key = "#roleId")
    @GetMapping("/{roleId}")
    public ApiResponseModel<Role> findRole(@PathVariable("roleId") Long roleId) throws ApiException {
        CRUDRoleRequestModel request = CRUDRoleRequestModel.builder().roleId(roleId).build();
        return this.findRoleTask.executeTask(request);
    }

    @CachePut(value = "role", key = "#roleId")
    @PostMapping("/{roleId}")
    public ApiResponseModel<Role> updateRole(@RequestBody CRUDRoleRequestModel request, @PathVariable("roleId") Long roleId) throws ApiException {
        request.setRoleId(roleId);
        return this.updateRoleTask.executeTask(request);
    }

    @CacheEvict(value = "role", allEntries = true)
    @DeleteMapping("/{roleId}")
    public ApiResponseModel<?> deleteRole(@PathVariable("roleId") Long roleId) throws ApiException {
        CRUDRoleRequestModel request = CRUDRoleRequestModel.builder().roleId(roleId).build();
        return this.deleteRoleTask.executeTask(request);
    }

    @PutMapping
    public ApiResponseModel<Role> createRole(@RequestBody CRUDRoleRequestModel request) throws ApiException {
        return this.createRoleTask.executeTask(request);
    }
}
