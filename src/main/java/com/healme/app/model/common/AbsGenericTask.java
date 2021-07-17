package com.healme.app.model.common;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.constant.PermissionCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.util.CommonUtils;
import com.healme.app.util.DateUtils;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Slf4j
public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel> {

    protected abstract void validateBusiness(Rq request) throws ApiException;

    protected abstract Rs processTask(Rq request) throws ApiException;

    public Rs executeTask(Rq request) throws ApiException {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
            log.info("I : method={}", stackTraceElement.getMethodName());
            log.info("I : Request={}", JsonConvertorUtils.toJson(request));

            this.validatePermissions(stackTraceElement);
            this.validateBusiness(request);
            Rs response = this.processTask(request);

            log.info("O : Response={}", JsonConvertorUtils.toJson(response));

            response.setSuccess(Boolean.TRUE);
            response.setTimestamp(DateUtils.nowISOString());
            return response;

        } catch (DataAccessException e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ApiException(ErrorCode.UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    protected void validatePermissions(StackTraceElement stackTraceElement) throws ClassNotFoundException {
        List<PermissionCode> permission = CommonUtils.getPermissions(stackTraceElement);
        log.info("I : permissions={}", permission);
    }

}
