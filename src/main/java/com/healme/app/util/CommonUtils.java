package com.healme.app.util;

import com.healme.app.common.annotation.RequiredPermissions;
import com.healme.app.common.constant.PermissionCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@UtilityClass
public class CommonUtils {

    public boolean isCause(Class<? extends Throwable> expected, Throwable exc) {
        return expected.isInstance(exc) || (exc != null && isCause(expected, exc.getCause()));
    }

    public List<PermissionCode> getPermissions(StackTraceElement stackTraceElement) throws ClassNotFoundException {
        List<PermissionCode> permissionList = new ArrayList<>();
        for (Method method : Class.forName(stackTraceElement.getClassName()).getMethods()) {
            if (method.getName().equals(stackTraceElement.getMethodName())) {
                RequiredPermissions permissions = AnnotationUtils.getAnnotation(method, RequiredPermissions.class);
                if (permissions != null) {
                    permissionList.addAll(Arrays.asList(permissions.groups()));
                }
            }
        }
        return permissionList;
    }
}
