package com.spring.app.service;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.permission.CreateMultiplePermissionRequestModel;
import com.spring.app.model.permission.CreateUpdatePermissionRequestModel;
import com.spring.app.repository.PermissionRepository;
import com.spring.app.repository.RoleRepository;
import com.spring.app.repository.entity.Permission;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission createPermission(CreateUpdatePermissionRequestModel request) {
        return this.permissionRepository.save(request.toPermission());
    }

    public List<Permission> createMultiplePermission(CreateMultiplePermissionRequestModel request) {
        List<Permission> permissions = new ArrayList<>();
        List<CreateUpdatePermissionRequestModel> createList = request.getList();

        if (CollectionUtils.isNotEmpty(createList)) {
            for (CreateUpdatePermissionRequestModel createPermission : createList) {
                permissions.add(createPermission.toPermission());
            }
        }

        return (List<Permission>) this.permissionRepository.saveAll(permissions);
    }

    @Transactional(readOnly = true)
    public Permission findPermissionById(long permissionId) throws ApiException {
        return this.permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, String.format("PermissionId(%s) not found.", permissionId)));
    }

    public Permission updatePermission(CreateUpdatePermissionRequestModel request, long permissionId) throws ApiException {
        Permission permission = this.findPermissionById(permissionId);
        permission.setName(request.getPermissionName());
        permission.setDescription(request.getPermissionDesc());
        return this.permissionRepository.save(permission);
    }

    public void deletePermissionById(long permissionId) throws ApiException {
        Permission permission = this.findPermissionById(permissionId);
        this.permissionRepository.delete(permission);
    }

    @Transactional(readOnly = true)
    public List<Permission> getPermissionsByPermissionId(List<Long> permissionIds) throws ApiException {
        List<Permission> permissions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissionIds)) {
            for (Long permissionId : permissionIds) {
                Permission permission = this.findPermissionById(permissionId);
                permissions.add(permission);
            }
        }
        return permissions;
    }
}
