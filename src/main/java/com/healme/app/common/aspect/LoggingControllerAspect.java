package com.healme.app.common.aspect;

import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingControllerAspect {

    @Around("@annotation(com.healme.app.common.annotation.LoggingController)")
    public ApiResponseModel process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Request : method={}", proceedingJoinPoint.getSignature().getName());
        log.info("Request : data={}", JsonConvertorUtils.toJson(proceedingJoinPoint.getArgs()));

        ApiResponseModel response = (ApiResponseModel) proceedingJoinPoint.proceed();

        log.info("Response : data={}", JsonConvertorUtils.toJson(response));
        return response;
    }
}
