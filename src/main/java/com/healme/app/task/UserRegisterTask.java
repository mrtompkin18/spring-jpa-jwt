package com.healme.app.task;

import com.healme.app.common.constant.ErrorConstant;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.AbsGenericTask;
import com.healme.app.model.user.UserRegisterRequestModel;
import com.healme.app.model.user.UserRegisterResponseModel;
import com.healme.app.repository.entity.User;
import com.healme.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterTask extends AbsGenericTask<UserRegisterRequestModel, UserRegisterResponseModel> {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void validateBusiness(UserRegisterRequestModel request) throws ApiException {
        if (StringUtils.isBlank(request.getUsername())) {
            throw new ApiException(ErrorConstant.REQUIRED, "Username is required.");
        } else if (StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ErrorConstant.REQUIRED, "Password is required.");
        } else if (StringUtils.isBlank(request.getEmail())) {
            throw new ApiException(ErrorConstant.REQUIRED, "Email is required.");
        }
    }

    @Override
    public UserRegisterResponseModel processTask(UserRegisterRequestModel request) throws ApiException {
        String email = request.getEmail();
        String username = request.getUsername();
        String password = request.getPassword();

        if (StringUtils.isAnyBlank(username, password, email)) {
            throw new ApiException(ErrorConstant.USER_ERROR_CODE, "Username, Password or Email is null");
        }

        User user = new User(username, this.passwordEncoder.encode(password), email);
        this.userService.create(user);

        UserRegisterResponseModel response = new UserRegisterResponseModel();
        response.setData(user);
        response.setMessage("insert user successfully");
        return response;
    }
}
   
