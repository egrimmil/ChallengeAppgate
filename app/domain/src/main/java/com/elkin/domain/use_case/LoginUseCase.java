package com.elkin.domain.use_case;

import com.elkin.commons.utils.Resource;
import com.elkin.commons.models.UserModel;

public interface LoginUseCase {
    void loginUser(UserModel userModel, String latitude, String longitude, Resource<String> id);
}
