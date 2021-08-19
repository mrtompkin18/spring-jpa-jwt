package com.spring.app.model.common.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiRequestModel;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.permission.RequiredPermissionModel;
import com.spring.app.model.common.user.UserDetailModel;
import com.spring.app.util.DateUtils;
import com.spring.app.util.JsonConvertorUtils;
import com.spring.app.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Slf4j
public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel> {

    protected abstract void validateBusiness(Rq request) throws ApiException;

    protected abstract Rs processTask(Rq request) throws ApiException;

    public Rs executeTask() throws ApiException {
        return this.executeTask(null);
    }

    public Rs executeTask(Rq request) throws ApiException {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            log.info("I : method={}", stackTraceElement.getMethodName());
            log.info("I : Request={}", JsonConvertorUtils.toJson(request));

            RequiredPermissionModel requiredPermissionModel = SecurityUtils.getPermissions(stackTraceElement);
            UserDetailModel userDetailModel = SecurityUtils.getUserDetail();

            this.validatePermissions(requiredPermissionModel, userDetailModel);
            this.validateBusiness(request);
            Rs response = this.processTask(request);

            log.info("O : Response={}", JsonConvertorUtils.toJson(response));

            response.setSuccess(Boolean.TRUE);
            response.setTimestamp(DateUtils.nowISOString());

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiException(ErrorCode.CIPHER_ERROR_CODE, e.getMessage());
        } catch (DataAccessException e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ApiException(ErrorCode.UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    protected void validatePermissions(RequiredPermissionModel requiredPermissionModel, UserDetailModel userDetail) throws ClassNotFoundException, ApiException {
        List<String> userPermissionCode = userDetail.getPermissionsCode();

        boolean isRequiredAll = requiredPermissionModel.isRequiredAll();
        List<String> servicePermissions = requiredPermissionModel.getPermissionCode();

        log.info("I : servicePermissions={} | isRequiredAll={}", servicePermissions, isRequiredAll);
        log.info("I : userPermissions={}", userPermissionCode);

        boolean isPermit;

        if (CollectionUtils.isEmpty(servicePermissions)) {
            return;
        } else if (isRequiredAll) {
            isPermit = CollectionUtils.containsAll(userPermissionCode, servicePermissions);
        } else {
            isPermit = CollectionUtils.containsAny(userPermissionCode, servicePermissions);
        }

        if (!isPermit) {
            throw new ApiException(ErrorCode.PERMISSION_REQUIRED_ERROR_CODE, "Permission required.");
        }
    }
}
