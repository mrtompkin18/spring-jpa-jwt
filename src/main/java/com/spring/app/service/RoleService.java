package com.spring.app.service;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.role.CreateRoleRequestModel;
import com.spring.app.repository.PermissionRepository;
import com.spring.app.repository.RoleRepository;
import com.spring.app.repository.entity.Permission;
import com.spring.app.repository.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional(rollbackFor = ApiException.class, propagation = Propagation.REQUIRES_NEW)
    public Role create(CreateRoleRequestModel request) throws ApiException {
        List<Long> permissionIds = request.getPermissions();
        List<Permission> permissions = new ArrayList<>();

        for (Long permissionId : permissionIds) {
            Permission permission = this.permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, String.format("PermissionId(%s) not found.", permissionId)));
            permissions.add(permission);
        }

        Role role = Role.builder()
                .name(request.getRoleName())
                .description(request.getRoleDescription())
                .permissions(permissions)
                .build();

        return this.roleRepository.save(role);
    }
}
