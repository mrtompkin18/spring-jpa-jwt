package com.healme.app.common.annotation;

import com.healme.app.common.constant.PermissionCode;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredPermissions {
    boolean requiredAll() default true;

    PermissionCode[] groups();
}
