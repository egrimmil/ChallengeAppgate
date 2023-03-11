package com.elkin.domain.use_case_impl;

import com.elkin.commons.models.UserModel;
import com.elkin.domain.repository.UserRepository;
import com.elkin.domain.use_case.LoginUseCase;
import com.elkin.commons.utils.Resource;

public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;

    public LoginUseCaseImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void loginUser(UserModel userModel, String latitude, String longitude, Resource<String> callback) {
        userRepository.getUserByEmailPass(userModel, latitude, longitude, callback);
    }
}
