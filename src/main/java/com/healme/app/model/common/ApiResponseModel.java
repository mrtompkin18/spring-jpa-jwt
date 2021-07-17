package com.healme.app.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healme.app.util.DateUtils;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseModel {

    @JsonProperty("error")
    protected String errorCode;

    @JsonProperty("error_description")
    protected String errorDescription;

    @JsonProperty("timestamp")
    protected String timestamp;

    @JsonProperty("success")
    protected Boolean success;

    public ApiResponseModel() {
        this.success = Boolean.TRUE;
        this.timestamp = DateUtils.ISO_OFFSET_DATE_TIME;
    }
}
