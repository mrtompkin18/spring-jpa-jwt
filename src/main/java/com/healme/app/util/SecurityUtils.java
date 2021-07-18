package com.healme.app.util;

import com.healme.app.common.constant.ErrorCode;
import com.healme.app.common.error.ApiException;
import com.healme.app.model.common.user.UserDetailModel;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {
    
    public UserDetailModel getUserDetail() throws ApiException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (UserDetailModel) authentication.getPrincipal();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.UNAUTHORIZE_ERROR_CODE, "Bad credential.");
        }
    }
}
