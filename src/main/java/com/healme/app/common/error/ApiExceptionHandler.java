package com.healme.app.common.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseModel handleBadRequestException(ApiException e) throws JsonProcessingException {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        apiResponseModel.setErrorCode(e.getErrorCode());
        apiResponseModel.setErrorDescription(e.getErrorDesc());
        apiResponseModel.setSuccess(Boolean.FALSE);
        log.error("Response : data={}", JsonConvertorUtils.toJson(apiResponseModel));
        return apiResponseModel;
    }
}
