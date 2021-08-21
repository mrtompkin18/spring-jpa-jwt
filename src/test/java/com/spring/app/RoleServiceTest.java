package com.spring.app;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.role.CRUDRoleRequestModel;
import com.spring.app.repository.entity.Role;
import com.spring.app.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Rollback(value = false)
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testCreateRole() throws ApiException {
        List<Long> permissionIds = Arrays.asList(1L, 2L, 3L);
        CRUDRoleRequestModel request = CRUDRoleRequestModel.builder()
                .roleName("ROLE_ADMIN")
                .permissions(permissionIds)
                .build();

        Role role = this.roleService.createRole(request);
        System.out.println("create :" + role.getRoleId());
    }

    @Test
    @Transactional
    public void testFindRole() throws ApiException {
        long roleId = 1;
        Role role = this.roleService.findRoleById(roleId);

        System.out.println("role : " + role.getName());
        System.out.println("permission: " + role.getPermissions().size());
    }

    @Test
    @Transactional
    public void testUpdateRole() throws ApiException {
        long roleId = 1;
        List<Long> permissionIds = Arrays.asList(1L, 2L, 3L);

        CRUDRoleRequestModel request = CRUDRoleRequestModel.builder()
                .roleId(roleId)
                .roleName("ROLE_SUPER_ADMIN")
                .permissions(permissionIds)
                .build();

        Role role = this.roleService.updateRole(request);
        System.out.println("role : " + role.getName());
        System.out.println("permission: " + role.getPermissions().size());
    }

    @Test
    public void testDeleteRole() throws ApiException {
        long roleId = 1;
        this.roleService.deleteRoleById(roleId);
    }
}
