package com.spring.app.util;

import com.spring.app.common.constant.PropertiesConstant;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Slf4j
@UtilityClass
public class CommonUtils {

    public boolean isCause(Class<? extends Throwable> expected, Throwable exc) {
        return expected.isInstance(exc) || (exc != null && isCause(expected, exc.getCause()));
    }

    public <A extends Annotation> A getAnnotationFromTraceStack(StackTraceElement stackTraceElement, Class<A> annotationType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Method method : Class.forName(stackTraceElement.getClassName()).getMethods()) {
            if (method.getName().equals(stackTraceElement.getMethodName())) {
                return AnnotationUtils.getAnnotation(method, annotationType);
            }
        }
        return null;
    }

    public boolean isDevMode(ConditionContext conditionContext) {
        Environment environment = conditionContext.getEnvironment();
        String isDevMode = environment.getProperty(PropertiesConstant.ENV_DEV_MODE, "false");
        return Boolean.parseBoolean(isDevMode);
    }
}
