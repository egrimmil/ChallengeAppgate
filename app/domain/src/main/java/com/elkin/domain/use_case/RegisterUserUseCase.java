package com.elkin.domain.use_case;

import com.elkin.commons.models.UserModel;
import com.elkin.commons.utils.Resource;

public interface RegisterUserUseCase {
    void registerUser(UserModel userModel, Resource<String> id);
}
