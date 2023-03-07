package com.elkin.challengeappgate.domain.repository;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.utils.Resource;

public interface UserRepository {
    void registerUser(UserModel userModel, Resource<String> id);
    void getUser(UserModel userModel, Resource<String> id);
}