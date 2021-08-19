package com.spring.app.model.common.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequiredPermissionModel {
    private boolean requiredAll;
    private List<String> permissionCode;
}
