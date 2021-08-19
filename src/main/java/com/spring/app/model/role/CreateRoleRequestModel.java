package com.spring.app.model.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.app.model.common.ApiRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequestModel extends ApiRequestModel {

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_desc")
    private String roleDescription;

    @JsonProperty("permissions")
    private List<Long> permissions;
}
