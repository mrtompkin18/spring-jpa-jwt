package com.spring.app.common.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.util.DateUtils;
import com.spring.app.util.JsonConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseModel<?>> handleBadRequestException(ApiException e) throws JsonProcessingException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiResponseModel<?> response = new ApiResponseModel<>();
        response.setErrorCode(e.getErrorCode());
        response.setErrorDescription(e.getErrorDesc());
        response.setTimestamp(DateUtils.nowISOString());
        response.setSuccess(Boolean.FALSE);

        log.error("O : Response={}", JsonConvertorUtils.toJson(response));

        if (ErrorCode.PERMISSION_REQUIRED_ERROR_CODE.equals(e.getErrorCode())) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }
}
