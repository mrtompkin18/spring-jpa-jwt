package com.spring.app.model.user;

import com.spring.app.model.common.ApiRequestModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserProfileRequestModel extends ApiRequestModel {
    private Long userId;
}
