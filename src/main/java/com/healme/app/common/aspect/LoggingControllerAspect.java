package com.healme.app.common.aspect;

import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.DateUtils;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class LoggingControllerAspect {

    @Around("@annotation(com.healme.app.common.annotation.LoggingController)")
    public ApiResponseModel process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Request : method={}", proceedingJoinPoint.getSignature().getName());
        log.info("Request : data={}", JsonConvertorUtils.toJson(proceedingJoinPoint.getArgs()));

        ApiResponseModel response = (ApiResponseModel) proceedingJoinPoint.proceed();
        if (Objects.isNull(response)) {
            response = new ApiResponseModel<>();
        }

        response.setCode(ErrorConstant.SUCCESS);
        response.setTimestamp(DateUtils.ISO_OFFSET_DATE_TIME);
        response.setStatus(HttpStatus.OK.value());

        log.info("Response : data={}", JsonConvertorUtils.toJson(response));
        return response;
    }
}
