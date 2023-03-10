package com.elkin.challengeappgate.domain.use_case_impl;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.repository.UserRepository;
import com.elkin.challengeappgate.domain.use_case.LoginUseCase;
import com.elkin.challengeappgate.utils.Resource;

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
