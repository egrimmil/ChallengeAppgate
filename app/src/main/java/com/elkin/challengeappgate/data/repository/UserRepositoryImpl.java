package com.elkin.challengeappgate.data.repository;

import android.util.Log;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.repository.UserRepository;
import com.elkin.challengeappgate.utils.Resource;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void registerUser(UserModel userModel, Resource<String> id) {
        Log.e("register", userModel.getUserName()+" "+userModel.getUserPass());
    }

    @Override
    public void getUser(UserModel userModel, Resource<String> id) {

    }
}
