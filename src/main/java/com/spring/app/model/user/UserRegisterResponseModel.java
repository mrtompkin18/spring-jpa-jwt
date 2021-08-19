package com.spring.app.model.user;

import com.spring.app.model.common.ApiResponseModel;
import com.spring.app.repository.entity.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterResponseModel extends ApiResponseModel {
    private User data;
}
