package com.healme.app.model.common;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ApiResponseModel {
    private String respCode;
    private String respDesc;
    private ZonedDateTime timestamp;
}
