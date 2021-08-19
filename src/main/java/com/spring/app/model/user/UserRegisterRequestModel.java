package com.spring.app.model.user;

import com.spring.app.model.common.ApiRequestModel;
import lombok.Data;

@Data
public class UserRegisterRequestModel extends ApiRequestModel {
    private String username;
    private String password;
    private String email;
}
