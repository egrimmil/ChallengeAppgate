package com.elkin.domain.use_case_impl;

import com.elkin.commons.utils.Resource;
import com.elkin.data.db.entities.Attempts;
import com.elkin.domain.repository.AttemptsRepository;
import com.elkin.domain.mappers.AttemptMapper;
import com.elkin.domain.models.AttemptModel;
import com.elkin.domain.use_case.HomeUseCase;

import java.util.ArrayList;
import java.util.List;

public class HomeUseCaseImpl implements HomeUseCase {

    private final AttemptsRepository attemptsRepository;

    public HomeUseCaseImpl(AttemptsRepository attemptsRepository){
        this.attemptsRepository = attemptsRepository;
    }

    @Override
    public void getAttemptsUser(int userId, Resource<List<AttemptModel>> callback) {
        attemptsRepository.getAttemptsUserId(userId, callback);
        /*callback.OnLoading(true);
        List<Attempts> list = attemptsRepository.getAttemptsUserId(userId);
        AttemptMapper attemptConverter = new AttemptMapper();
        List<AttemptModel> listAttemptModel = new ArrayList<>();
        for (Attempts attempts : list) {
            listAttemptModel.add(attemptConverter.fromEntity(attempts));
        }
        callback.OnLoading(false);
        callback.OnSuccess(listAttemptModel);*/
    }
}
