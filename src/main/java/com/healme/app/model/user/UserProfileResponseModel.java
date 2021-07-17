package com.healme.app.model.user;

import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.repository.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfileResponseModel extends ApiResponseModel {
    private User data;
}
