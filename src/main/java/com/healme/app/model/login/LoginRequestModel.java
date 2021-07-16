package com.healme.app.model.login;

import com.healme.app.model.common.ApiRequestModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestModel extends ApiRequestModel {
    private String username;
    private String password;
}
