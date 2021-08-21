package com.spring.app.model.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.app.common.constant.ApiConstant;
import com.spring.app.model.common.ApiRequestModel;
import com.spring.app.repository.entity.Permission;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdatePermissionRequestModel extends ApiRequestModel {
    @JsonProperty("permission_name")
    private String permissionName;

    @JsonProperty("permission_desc")
    private String permissionDesc;

    @JsonIgnore
    public Permission toPermission() {
        return Permission.builder()
                .name(this.permissionName)
                .description(this.permissionDesc)
                .flag(ApiConstant.FLAG.A.name())
                .build();
    }
}
