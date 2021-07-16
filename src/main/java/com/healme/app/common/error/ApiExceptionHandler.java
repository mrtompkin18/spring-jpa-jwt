package com.healme.app.common.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ApiException {

    @Serial
    private static final long serialVersionUID = -7505154337168821591L;

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseModel handleException(ApiException apiException) throws JsonProcessingException {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        apiResponseModel.setRespCode(apiException.getErrorCode());
        apiResponseModel.setRespDesc(apiException.getErrorDesc());
        apiResponseModel.setTimestamp(LocalDateTime.now());
        log.error("Response : data={}", JsonConvertorUtils.toJson(apiResponseModel));
        return apiResponseModel;
    }
}
