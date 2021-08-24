package com.spring.app.model.common.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiRequestModel;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.pagination.PaginationModel;
import com.spring.app.model.common.permission.RequiredPermissionModel;
import com.spring.app.model.common.user.UserDetailModel;
import com.spring.app.util.DateUtils;
import com.spring.app.util.JsonConvertorUtils;
import com.spring.app.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel<?>> {

    protected void validateBusiness(Rq request) throws ApiException {
        // waiting for implement
    }

    protected abstract Rs processTask(Rq request) throws ApiException;

    public Rs executeTask() throws ApiException {
        return this.executeTask(null);
    }

    public Rs executeTask(Rq request) throws ApiException {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
            log.info("I : method={}", stackTraceElement.getMethodName());
            log.info("I : Request={}", JsonConvertorUtils.toJson(request));

            RequiredPermissionModel requiredPermissionModel = SecurityUtils.getPermissions(stackTraceElement);

            this.validatePermissions(requiredPermissionModel);
            this.validateBusiness(request);

            Rs response = this.processTask(request);
            response.setSuccess(Boolean.TRUE);
            response.setTimestamp(DateUtils.nowISOString());

            log.info("O : Response={}", JsonConvertorUtils.toJson(response));

            return response;

        } catch (JsonProcessingException e) {
            throw new ApiException(ErrorCode.CIPHER_ERROR_CODE, e.getMessage());
        } catch (DataAccessException e) {
            throw new ApiException(ErrorCode.DATABASE_ERROR_CODE, e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ApiException(ErrorCode.UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    protected void validatePermissions(RequiredPermissionModel requiredPermissionModel) throws ClassNotFoundException, ApiException {
        Optional<UserDetailModel> userDetailModel = SecurityUtils.getUserDetail();
        List<String> userPermissionCode = Collections.emptyList();

        if (userDetailModel.isPresent()) {
            UserDetailModel exists = userDetailModel.get();
            userPermissionCode = exists.getPermissionsCode();
        }

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

    protected <T> ApiResponseModel<List<T>> getPagingResponse(PaginationModel<T> pagination) {
        ApiResponseModel<List<T>> response = new ApiResponseModel<>();
        response.setData(pagination.getList());
        response.setFiltered(pagination.getFiltered());
        response.setTotal(pagination.getTotal());
        return response;
    }
}
