package com.healme.app.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healme.app.model.common.ApiResponseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class LoginResponseModel extends ApiResponseModel {

    @JsonProperty("token")
    private String token;

    @JsonProperty("type")
    private String type;

    @JsonProperty("expire_at")
    private LocalDateTime expiredAt;
}
