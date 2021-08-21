package com.spring.app.task.auth;

import com.spring.app.common.config.ApplicationConfig;
import com.spring.app.common.constant.ApiConstant;
import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.login.LoginRequestModel;
import com.spring.app.model.login.LoginResponseModel;
import com.spring.app.repository.entity.User;
import com.spring.app.service.TokenService;
import com.spring.app.service.UserService;
import com.spring.app.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoginTask extends AbsGenericTask<LoginRequestModel, ApiResponseModel<LoginResponseModel>> {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    protected void validateBusiness(LoginRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new ApiException(ErrorCode.REQUIRED, "Username is required.");
        } else if (StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ErrorCode.REQUIRED, "Password is required.");
        }

        User user = this.userService.findByUsername(request.getUsername());

        boolean isPasswordMatched = this.userService.isPasswordMatched(request.getPassword(), user.getPassword());
        if (!isPasswordMatched) {
            throw new ApiException(ErrorCode.USER_ERROR_CODE, "Password is incorrect.");
        }
    }

    @Override
    protected ApiResponseModel<LoginResponseModel> processTask(LoginRequestModel request) throws ApiException {
        User user = this.userService.findByUsername(request.getUsername());

        long expireTimeInSecond = Long.parseLong(this.applicationConfig.getJwtExpireTimeInSecond());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plusSeconds(expireTimeInSecond);

        String token = this.tokenService.generateToken(user, DateUtils.localDateTimeToDate(expiredAt));

        LoginResponseModel data = LoginResponseModel.builder()
                .token(token)
                .expiredAt(expiredAt)
                .type(ApiConstant.TOKEN_BEARER_TYPE)
                .build();

        return new ApiResponseModel<>(data);
    }
}
