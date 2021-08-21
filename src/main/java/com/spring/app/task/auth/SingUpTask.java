package com.spring.app.task.auth;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.user.UserRegisterRequestModel;
import com.spring.app.repository.entity.User;
import com.spring.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SingUpTask extends AbsGenericTask<UserRegisterRequestModel, ApiResponseModel<User>> {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void validateBusiness(UserRegisterRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new ApiException(ErrorCode.REQUIRED, "Username is required.");
        } else if (StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ErrorCode.REQUIRED, "Password is required.");
        } else if (StringUtils.isBlank(request.getEmail())) {
            throw new ApiException(ErrorCode.REQUIRED, "Email is required.");
        }


        boolean isExistsUsername = this.userService.existsByUsername(request.getUsername());
        if (isExistsUsername) {
            throw new ApiException(ErrorCode.DUPLICATED, "Email is already used.");
        }

        boolean isExistsEmail = this.userService.existsByEmail(request.getEmail());
        if (isExistsEmail) {
            throw new ApiException(ErrorCode.DUPLICATED, "Email is already used.");
        }
    }

    @Override
    protected ApiResponseModel<User> processTask(UserRegisterRequestModel request) throws ApiException {
        String email = request.getEmail();
        String username = request.getUsername();
        String password = request.getPassword();

        User user = User.builder()
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .email(email)
                .build();

        this.userService.create(user);

        return new ApiResponseModel<>(user);
    }
}
   
