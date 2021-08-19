package com.spring.app.task.user;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.common.user.UserDetailModel;
import com.spring.app.model.user.UserProfileRequestModel;
import com.spring.app.model.user.UserProfileResponseModel;
import com.spring.app.repository.entity.User;
import com.spring.app.service.UserService;
import com.spring.app.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        User user = this.userService.findById(userDetailModel.getUserId())
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "User not found."));

        return UserProfileResponseModel.builder()
                .data(user)
                .build();
    }
}
