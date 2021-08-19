package com.spring.app.model.login;

import com.spring.app.model.common.ApiRequestModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestModel extends ApiRequestModel {
    private String username;
    private String password;
}
