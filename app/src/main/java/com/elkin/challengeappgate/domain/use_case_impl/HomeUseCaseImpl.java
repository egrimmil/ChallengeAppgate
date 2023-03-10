package com.elkin.challengeappgate.domain.use_case_impl;

import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.domain.repository.AttemptsRepository;
import com.elkin.challengeappgate.domain.use_case.HomeUseCase;
import com.elkin.challengeappgate.utils.Resource;

import java.util.List;

public class HomeUseCaseImpl implements HomeUseCase {

    private final AttemptsRepository attemptsRepository;

    public HomeUseCaseImpl(AttemptsRepository attemptsRepository){
        this.attemptsRepository = attemptsRepository;
    }

    @Override
    public void getAttemptsUser(int userId, Resource<List<Attempts>> attempts) {
        attemptsRepository.getAttemptsUserId(userId, attempts);
    }
}
