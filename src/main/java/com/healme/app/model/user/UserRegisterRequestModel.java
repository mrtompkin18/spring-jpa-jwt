package com.healme.app.model.user;

import com.healme.app.model.common.ApiRequestModel;
import lombok.Data;

@Data
public class UserRegisterRequestModel extends ApiRequestModel {
    private String username;
    private String password;
    private String email;
}
