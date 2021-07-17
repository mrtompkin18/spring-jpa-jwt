package com.healme.app.model.login;

import com.healme.app.model.common.ApiResponseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class LoginResponseModel extends ApiResponseModel {
    private String accessToken;
    private String type;
}
