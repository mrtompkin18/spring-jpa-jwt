package com.healme.app.service;

import com.healme.app.common.constant.ApiConstant;
import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.role.CreateRoleRequestModel;
import com.healme.app.repository.PermissionRepository;
import com.healme.app.repository.RoleRepository;
import com.healme.app.repository.entity.PermissionEntity;
import com.healme.app.repository.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public RoleEntity create(CreateRoleRequestModel request) throws ApiException {
        List<Long> permissionIds = request.getPermissions();
        Iterable<PermissionEntity> permissionList = this.permissionRepository.findAllById(permissionIds);

        List<PermissionEntity> permissions = new ArrayList<>();
        for (PermissionEntity permission : permissionList) {
            permissions.add(permission);
        }

        if (permissions.size() != permissionIds.size()) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Permission id mismatch!");
        }

        RoleEntity role = RoleEntity.builder()
                .name(request.getRoleName())
                .description(request.getRoleDescription())
                .flag(ApiConstant.FLAG.A.name())
                .permissions(permissions)
                .build();

        return this.roleRepository.save(role);
    }
}
