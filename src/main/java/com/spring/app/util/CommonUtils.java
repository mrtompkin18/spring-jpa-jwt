package com.spring.app.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

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
}
