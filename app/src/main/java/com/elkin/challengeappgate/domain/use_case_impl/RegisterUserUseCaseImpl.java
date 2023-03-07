package com.elkin.challengeappgate.domain.use_case_impl;

import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.repository.UserRepository;
import com.elkin.challengeappgate.domain.use_case.RegisterUserUseCase;
import com.elkin.challengeappgate.utils.Resource;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserModel userModel, Resource<String> id) {
        userRepository.registerUser(userModel, id);
    }
}