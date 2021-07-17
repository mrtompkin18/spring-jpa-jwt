package com.healme.app.common.error;

import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.util.DateUtils;
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
    public ApiResponseModel handleBadRequestException(ApiException e) {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        apiResponseModel.setCode(e.getErrorCode());
        apiResponseModel.setMessage(e.getErrorDesc());
        apiResponseModel.setTimestamp(DateUtils.ISO_OFFSET_DATE_TIME);
        log.error("Response : data={}", JsonConvertorUtils.toJson(apiResponseModel));
        return apiResponseModel;
    }
}
