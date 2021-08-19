package com.spring.app.model.user;

import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.repository.entity.User;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseModel extends ApiResponseModel {
    private User data;
}
