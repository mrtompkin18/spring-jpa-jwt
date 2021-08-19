package com.spring.app.model.common.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionModel {
    private Long permissionId;
    private String name;
    private String description;
    private String flag;
}
