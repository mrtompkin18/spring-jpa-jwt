package com.healme.app.model.common;

import com.healme.app.common.constant.ErrorConstant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponseModel {
    protected String respCode;
    protected String respDesc;
    protected LocalDateTime timestamp;

    public ApiResponseModel() {
        this.respCode = ErrorConstant.SUCCESS;
        this.timestamp = LocalDateTime.now();
    }
}
