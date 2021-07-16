package com.healme.app.common.error;

import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.DateUtils;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    private ApiResponseModel initResponse(String code, String message) {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        apiResponseModel.setCode(code);
        apiResponseModel.setMessage(message);
        apiResponseModel.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponseModel.setTimestamp(DateUtils.ISO_OFFSET_DATE_TIME);
        return apiResponseModel;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseModel handleBadRequestException(ApiException e) {
        ApiResponseModel apiResponseModel = this.initResponse(e.getErrorCode(), e.getErrorDesc());
        log.error("Response : data={}", JsonConvertorUtils.toJson(apiResponseModel));
        return apiResponseModel;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseModel handleDataAccessException(DataAccessException e) {
        ApiResponseModel apiResponseModel = this.initResponse(ErrorConstant.DATABASE_ERROR_CODE, e.getMessage());
        log.error("Response : data={}", JsonConvertorUtils.toJson(apiResponseModel));
        return apiResponseModel;
    }
}
