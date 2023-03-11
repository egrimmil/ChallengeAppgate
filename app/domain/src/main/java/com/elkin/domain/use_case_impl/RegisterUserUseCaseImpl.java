package com.elkin.domain.use_case_impl;

import com.elkin.commons.models.UserModel;
import com.elkin.domain.repository.UserRepository;
import com.elkin.domain.use_case.RegisterUserUseCase;
import com.elkin.commons.utils.Resource;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserModel userModel, Resource<String> callback) {
        userRepository.registerUser(userModel, callback);
    }
}