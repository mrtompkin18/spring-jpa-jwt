package com.healme.app.common.error;

import com.healme.app.model.common.ApiResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ApiException {

    @Serial
    private static final long serialVersionUID = -7505154337168821591L;

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseModel handleException(ApiException apiException) {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        apiResponseModel.setRespCode(apiException.getErrorCode());
        apiResponseModel.setRespDesc(apiException.getErrorDesc());
        apiResponseModel.setTimestamp(ZonedDateTime.now());
        return apiResponseModel;
    }
}
