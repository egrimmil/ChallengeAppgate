package com.elkin.challengeappgate.domain.use_case;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.utils.Resource;

public interface LoginUseCase {
    void loginUser(UserModel userModel, String latitude, String longitude, Resource<String> id);
}
