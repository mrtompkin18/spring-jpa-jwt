package com.spring.app.service;

import com.spring.app.common.constant.ApiConstant;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.pagination.PaginationModel;
import com.spring.app.model.role.CRUDRoleRequestModel;
import com.spring.app.model.role.InquiryRoleRequestModel;
import com.spring.app.repository.RoleRepository;
import com.spring.app.repository.custom.CustomRoleRepository;
import com.spring.app.repository.entity.Permission;
import com.spring.app.repository.entity.Role;
import com.spring.app.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomRoleRepository customRoleRepository;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    public PaginationModel<Role> inquiryByCriteria(InquiryRoleRequestModel request) throws ApiException {
        return this.customRoleRepository.inquiryRole(request);
    }

    public Role createRole(CRUDRoleRequestModel request) throws ApiException {
        List<Permission> permissions = this.permissionService.getPermissionsByPermissionId(request.getPermissions());
        Role role = Role.builder()
                .name(request.getRoleName())
                .description(request.getRoleDescription())
                .flag(ApiConstant.FLAG.A.name())
                .permissions(permissions)
                .build();

        return this.roleRepository.save(role);
    }

    @CachePut(value = "role", key = "#roleId")
    public Role updateRole(CRUDRoleRequestModel request) throws ApiException {
        List<Long> permissionIds = request.getPermissions();
        List<Permission> permissions = this.permissionService.getPermissionsByPermissionId(permissionIds);

        Role role = this.findRoleById(request.getRoleId());
        role.setName(request.getRoleName());
        role.setDescription(request.getRoleDescription());
        role.setPermissions(permissions);

        return this.roleRepository.save(role);
    }

    @Cacheable(value = "role", key = "#roleId")
    @Transactional(readOnly = true)
    public Role findRoleById(Long roleId) throws ApiException {
        return this.roleRepository.findById(roleId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, String.format("Role(%s) not found.", roleId)));
    }

    @CacheEvict(value = "role", allEntries = true)
    public void deleteRoleById(Long roleId) throws ApiException {
        Role role = this.findRoleById(roleId);
        this.roleRepository.delete(role);
    }

    @Transactional(readOnly = true)
    public Role findRoleByUserId(long userId) {
        return this.roleRepository.findByUserId(userId);
    }

    public Role removeUserOutOfRole(long roleId, long userId) throws ApiException {
        User user = this.userService.findById(userId);
        Role role = this.findRoleById(roleId);
        role.removeUser(user);
        return this.roleRepository.save(role);
    }
}
