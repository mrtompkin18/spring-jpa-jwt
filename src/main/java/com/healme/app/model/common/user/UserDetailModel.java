package com.healme.app.model.common.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailModel {
    private Long userId;
    private Long roleId;
    private List<String> permissionsCode;
}
