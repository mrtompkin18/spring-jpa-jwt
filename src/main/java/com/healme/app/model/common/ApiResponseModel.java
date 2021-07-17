package com.healme.app.model.common;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.util.DateUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponseModel {
    protected String code;
    protected String message;
    protected Integer status;
    protected String timestamp;

    public ApiResponseModel() {
        this.message = "success";
        this.code = ErrorCode.SUCCESS;
        this.status = HttpStatus.OK.value();
        this.timestamp = DateUtils.ISO_OFFSET_DATE_TIME;

    }
}
