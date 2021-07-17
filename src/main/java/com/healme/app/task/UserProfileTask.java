package com.healme.app.task;

import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.AbsGenericTask;
import com.healme.app.model.user.UserProfileRequestModel;
import com.healme.app.model.user.UserProfileResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserProfileTask extends AbsGenericTask<UserProfileRequestModel, UserProfileResponseModel> {

    @Override
    protected void validateBusiness(UserProfileRequestModel request) throws ApiException {

    }
    
    @Override
    protected UserProfileResponseModel processTask(UserProfileRequestModel request) throws ApiException {
        return new UserProfileResponseModel();
    }
}
