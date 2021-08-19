package com.spring.app.task.auth;

import com.spring.app.common.constant.ErrorCode;
import com.spring.app.common.error.ApiException;
import com.spring.app.model.common.task.AbsGenericTask;
import com.spring.app.model.user.UserRegisterRequestModel;
import com.spring.app.model.user.UserRegisterResponseModel;
import com.spring.app.repository.entity.User;
import com.spring.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SingUpTask extends AbsGenericTask<UserRegisterRequestModel, UserRegisterResponseModel> {

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
    }

    @Override
    protected UserRegisterResponseModel processTask(UserRegisterRequestModel request) throws ApiException {
        String email = request.getEmail();
        String username = request.getUsername();
        String password = request.getPassword();

        User userEntity = User.builder()
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .email(email)
                .build();

        this.userService.create(userEntity);

        return UserRegisterResponseModel.builder()
                .data(userEntity)
                .build();
    }
}
   
