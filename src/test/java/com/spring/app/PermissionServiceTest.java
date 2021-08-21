package com.spring.app;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.permission.CreateMultiplePermissionRequestModel;
import com.spring.app.model.permission.CreateUpdatePermissionRequestModel;
import com.spring.app.repository.entity.Permission;
import com.spring.app.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Rollback(value = false)
@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    @Transactional
    public void testCreateMultiplePermission() {
        CreateUpdatePermissionRequestModel read = CreateUpdatePermissionRequestModel.builder().permissionName("READ").build();
        CreateUpdatePermissionRequestModel edit = CreateUpdatePermissionRequestModel.builder().permissionName("EDIT").build();
        CreateUpdatePermissionRequestModel delete = CreateUpdatePermissionRequestModel.builder().permissionName("DELETE").build();

        List<CreateUpdatePermissionRequestModel> list = Arrays.asList(read, edit, delete);
        CreateMultiplePermissionRequestModel request = CreateMultiplePermissionRequestModel.builder().list(list).build();

        List<Permission> permissionList = this.permissionService.createMultiplePermission(request);
        System.out.println("create : " + permissionList.size());
    }

    @Test
    @Transactional
    public void testCreateSinglePermission() {
        CreateUpdatePermissionRequestModel request = CreateUpdatePermissionRequestModel.builder().permissionName("APPROVE").build();
        Permission permission = this.permissionService.createPermission(request);
        System.out.println("create : " + permission.getName());
    }

    @Test
    @Transactional
    public void testUpdatePermission() throws ApiException {
        long permissionId = 4;
        CreateUpdatePermissionRequestModel request = CreateUpdatePermissionRequestModel.builder().permissionName("SPECIAL").build();
        Permission permission = this.permissionService.updatePermission(request, permissionId);
        System.out.println("create : " + permission.getName());
    }

    @Test
    @Transactional
    public void testFindPermission() throws ApiException {
        long permissionId = 1;
        Permission permission = this.permissionService.findPermissionById(permissionId);
        System.out.println("find : " + permission.getName());
    }

    @Test
    @Transactional
    public void testDeletePermission() throws ApiException {
        long permissionId = 4;
        this.permissionService.deletePermissionById(permissionId);
    }

    @Test
    public void testListPermission() throws ApiException {
        List<Long> permissionIds = Arrays.asList(1L, 2L, 3L);
        List<Permission> permissions = this.permissionService.getPermissionsByPermissionId(permissionIds);
        System.out.println("list : " + permissions.size());
    }
}
