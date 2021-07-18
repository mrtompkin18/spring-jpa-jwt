package com.healme.app.task;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.task.AbsGenericTask;
import com.healme.app.model.common.user.UserDetailModel;
import com.healme.app.model.user.UserProfileRequestModel;
import com.healme.app.model.user.UserProfileResponseModel;
import com.healme.app.repository.entity.UserEntity;
import com.healme.app.service.UserService;
import com.healme.app.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProfileTask extends AbsGenericTask<UserProfileRequestModel, UserProfileResponseModel> {

    @Autowired
    private UserService userService;

    @Override
    protected void validateBusiness(UserProfileRequestModel request) throws ApiException {

    }

    @Override
    protected UserProfileResponseModel processTask(UserProfileRequestModel request) throws ApiException {
        UserDetailModel userDetailModel = SecurityUtils.getUserDetail();
        Optional<UserEntity> user = this.userService.findById(userDetailModel.getUserId());

        if (user.isEmpty()) {
            throw new ApiException(ErrorCode.NOT_FOUND, "User not found");
        }

        return UserProfileResponseModel.builder()
                .data(user.get())
                .build();
    }
}
