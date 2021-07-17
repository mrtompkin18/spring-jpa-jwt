package com.healme.app.model.login;

import com.healme.app.model.common.ApiResponseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class LoginResponseModel extends ApiResponseModel {
    private String token;
    private String type;
    private LocalDateTime expiredAt;
}
