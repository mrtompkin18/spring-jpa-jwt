package com.healme.app.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredPermissions {
    boolean requiredAll() default true;

    String[] groups();
}
