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
    private User user;

    public UserRegisterResponseModel(User user, String message) {
        this.user = user;
        this.respDesc = message;
    }
}
