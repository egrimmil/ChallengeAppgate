package com.elkin.domain.repository;

import com.elkin.commons.models.UserModel;
import com.elkin.commons.utils.Resource;

public interface UserRepository {
    void registerUser(UserModel userModel, Resource<String> callback);
    void getUserByEmailPass(UserModel userModel,  String latitude, String longitude, Resource<String> callback);
}