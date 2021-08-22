package com.spring.app.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseModel<T> implements Serializable {

    @JsonProperty("error")
    protected String errorCode;

    @JsonProperty("error_description")
    protected String errorDescription;

    @JsonProperty("timestamp")
    protected String timestamp;

    @JsonProperty("success")
    protected Boolean success;

    @JsonProperty("data")
    protected T data;

    @JsonProperty("filtered")
    private Integer filtered;

    @JsonProperty("total")
    private Integer total;

    public ApiResponseModel(T data) {
        this.data = data;
    }
}
