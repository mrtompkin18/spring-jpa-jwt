package com.spring.app.common.condition;

import com.spring.app.util.CommonUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DevCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return CommonUtils.isDevMode(conditionContext.getEnvironment());
    }
}
