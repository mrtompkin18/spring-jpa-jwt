package com.healme.app.model.user;

import com.healme.app.model.common.ApiResponseModel;
import com.healme.app.repository.entity.User;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterResponseModel extends ApiResponseModel {
    private User data;
}
