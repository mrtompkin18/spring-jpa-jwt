package com.healme.app.model.common;

import com.healme.app.common.constant.ApiConstant;
import com.healme.app.common.constant.ErrorCode;
import com.healme.app.util.DateUtils;
import lombok.Data;

@Data
public class ApiResponseModel {
    protected String code;
    protected String message;
    protected String timestamp;

    public ApiResponseModel() {
        this.message = ApiConstant.SUCCESS;
        this.code = ErrorCode.SUCCESS;
        this.timestamp = DateUtils.ISO_OFFSET_DATE_TIME;
    }
}
