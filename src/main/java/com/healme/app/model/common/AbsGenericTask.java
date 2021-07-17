package com.healme.app.model.common;

import com.healme.app.common.annotation.RequiredPermissions;
import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel> {

    protected abstract void validateBusiness(Rq request) throws ApiException;

    protected abstract Rs processTask(Rq request) throws ApiException;

    public Rs executeTask(Rq request) throws ApiException {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
            log.info("I : method={}", stackTraceElement.getMethodName());
            log.info("I : Request={}", JsonConvertorUtils.toJson(request));

            this.authorizeProcess(stackTraceElement);
            this.validateBusiness(request);
            Rs rs = this.processTask(request);

            log.info("O : Response={}", JsonConvertorUtils.toJson(rs));
            return rs;

        } catch (DataAccessException e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ApiException(ErrorCode.UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    protected void authorizeProcess(StackTraceElement stackTraceElement) throws ClassNotFoundException {
        for (Method method : Class.forName(stackTraceElement.getClassName()).getMethods()) {
            if (method.getName().equals(stackTraceElement.getMethodName())) {
                RequiredPermissions permissions = AnnotationUtils.getAnnotation(method, RequiredPermissions.class);
                if (permissions != null) {
                    log.info("I : permissions={}", Arrays.toString(permissions.groups()));
                }
            }
        }
    }

}
