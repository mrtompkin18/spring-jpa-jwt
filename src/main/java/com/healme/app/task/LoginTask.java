package com.healme.app.task;

import com.healme.app.common.constant.ApiConstant;
import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.AbsGenericTask;
import com.healme.app.model.login.LoginRequestModel;
import com.healme.app.model.login.LoginResponseModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.TokenService;
import com.healme.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginTask extends AbsGenericTask<LoginRequestModel, LoginResponseModel> {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void validateBusiness(LoginRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new ApiException(ErrorCode.REQUIRED, "Username is required.");
        } else if (StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ErrorCode.REQUIRED, "Password is required.");
        }

        Optional<User> user = this.userService.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            throw new ApiException(ErrorCode.USER_ERROR_CODE, "Username is not found.");
        }

        boolean isPasswordMatched = this.userService.isPasswordMatched(request.getPassword(), user.get().getPassword());
        if (!isPasswordMatched) {
            throw new ApiException(ErrorCode.USER_ERROR_CODE, "Password is incorrect.");
        }
    }

    @Override
    protected LoginResponseModel processTask(LoginRequestModel request) throws ApiException {
        Optional<User> user = this.userService.findByUsername(request.getUsername());

        String token = this.tokenService.generateToken(user.get());

        return LoginResponseModel.builder()
                .accessToken(token)
                .type(ApiConstant.TOKEN_BEARER_TYPE)
                .build();

    }
}
