package com.healme.app.task;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.AbsGenericTask;
import com.healme.app.model.user.UserProfileRequestModel;
import com.healme.app.model.user.UserProfileResponseModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.UserService;
import com.healme.app.util.SecurityContextUtils;
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
        Optional<Long> userId = SecurityContextUtils.getUserId();

        if (userId.isEmpty()) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Bad credentials");
        }

        Optional<User> user = this.userService.findById(userId.get());

        return UserProfileResponseModel.builder()
                .data(user.get())
                .build();
    }
}
