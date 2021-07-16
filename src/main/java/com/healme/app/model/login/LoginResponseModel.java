package com.healme.app.model.login;

import com.healme.app.model.common.ApiResponseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponseModel extends ApiResponseModel {
    private String accessToken;
    private String type;
    private String expireTime;
}
