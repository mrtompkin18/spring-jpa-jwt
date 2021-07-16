package com.healme.app.model.common;

import lombok.Data;

@Data
public class ApiResponseModel<T> {
    protected String code;
    protected String message;
    protected Integer status;
    protected String timestamp;
    protected T data;
}
