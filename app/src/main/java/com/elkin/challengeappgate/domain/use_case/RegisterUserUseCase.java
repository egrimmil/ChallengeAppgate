package com.elkin.challengeappgate.domain.use_case;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.utils.Resource;

public interface RegisterUserUseCase {
    void registerUser(UserModel userModel, Resource<String> id);
}
