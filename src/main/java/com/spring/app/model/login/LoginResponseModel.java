package com.spring.app.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginResponseModel {

    @JsonProperty("token")
    private String token;

    @JsonProperty("type")
    private String type;

    @JsonProperty("expire_at")
    private LocalDateTime expiredAt;
}
