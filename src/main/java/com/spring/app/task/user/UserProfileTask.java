package com.spring.app.task.user;

import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.user.UserProfileRequestModel;
import com.spring.app.repository.entity.User;
import com.spring.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileTask extends AbsGenericTask<UserProfileRequestModel, ApiResponseModel<User>> {

    @Autowired
    private UserService userService;

    @Override
    protected ApiResponseModel<User> processTask(UserProfileRequestModel request) throws ApiException {
        User user = this.userService.findById(request.getUserId());

        return new ApiResponseModel<>(user);
    }
}
