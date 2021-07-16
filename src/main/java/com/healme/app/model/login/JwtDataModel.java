package com.healme.app.model.login;

import lombok.Data;

@Data
public class JwtDataModel {
    private String accessToken;
    private String type;
}
