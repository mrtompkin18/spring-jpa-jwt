package com.healme.app.util;

import com.healme.app.common.annotation.RequiredPermissions;
import com.healme.app.model.common.permission.RequiredPermissionModel;
import com.healme.app.model.common.user.UserDetailModel;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class SecurityUtils {

    public RequiredPermissionModel getPermissions(StackTraceElement stackTraceElement) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<String> permissionList = Collections.emptyList();
        boolean requiredAll = false;

        RequiredPermissions permissions = CommonUtils.getAnnotationFromTraceStack(stackTraceElement, RequiredPermissions.class);
        if (permissions != null) {
            requiredAll = permissions.requiredAll();
            permissionList = Arrays.asList(permissions.groups());
        }

        return RequiredPermissionModel.builder()
                .permissionCode(permissionList)
                .requiredAll(requiredAll)
                .build();
    }

    public UserDetailModel getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object object = authentication.getPrincipal();
            if (object instanceof UserDetailModel) {
                return (UserDetailModel) object;
            }
        }
        return UserDetailModel.builder().build();
    }
}