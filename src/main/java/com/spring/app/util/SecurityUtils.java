package com.spring.app.util;

import com.spring.app.common.annotation.RequiredPermissions;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.permission.RequiredPermissionModel;
import com.spring.app.model.common.user.UserDetailModel;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

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

    public Optional<UserDetailModel> getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object object = authentication.getPrincipal();
            if (object instanceof UserDetailModel) {
                return Optional.of((UserDetailModel) object);
            }
        }
        return Optional.empty();
    }

    public UserDetailModel getUserDetailOrThrow() throws ApiException {
        return getUserDetail().orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "User cannot be null!"));
    }
}
