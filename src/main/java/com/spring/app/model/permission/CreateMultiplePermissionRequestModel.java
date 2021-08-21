package com.spring.app.model.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMultiplePermissionRequestModel {
    private List<CreateUpdatePermissionRequestModel> list = new ArrayList<>();
}
