package com.spring.app.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}
