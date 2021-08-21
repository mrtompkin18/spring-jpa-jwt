package com.spring.app.model.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.app.model.common.ApiRequestModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("roleId")
public class CRUDRoleRequestModel extends ApiRequestModel {

    private Long roleId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_desc")
    private String roleDescription;

    @JsonProperty("permissions")
    private List<Long> permissions = new ArrayList<>();
}
