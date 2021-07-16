package com.healme.app.task;

import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.AbsGenericTask;
import com.healme.app.model.login.LoginRequestModel;
import com.healme.app.model.login.LoginResponseModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginTask extends AbsGenericTask<LoginRequestModel, LoginResponseModel> {

    @Autowired
    private UserService userService;

    @Override
    protected void validateBusiness(LoginRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new ApiException(ErrorConstant.REQUIRED, "Username is required.");
        } else if (StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ErrorConstant.REQUIRED, "Password is required.");
        }

        Optional<User> user = this.userService.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            throw new ApiException(ErrorConstant.USER_ERROR_CODE, "Username is not found.");
        }

        boolean isPasswordMatched = this.userService.isPasswordMatched(request.getPassword(), user.get().getPassword());
        if (!isPasswordMatched) {
            throw new ApiException(ErrorConstant.USER_ERROR_CODE, "Password is incorrect.");
        }
    }

    @Override
    protected LoginResponseModel processTask(LoginRequestModel request) throws ApiException {
        return new LoginResponseModel();
    }
}
