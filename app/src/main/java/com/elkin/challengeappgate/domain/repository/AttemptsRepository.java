package com.elkin.challengeappgate.domain.repository;

import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.utils.Resource;

import java.util.List;

public interface AttemptsRepository {
    void getAttemptsUserId(int userId, Resource<List<Attempts>> attempts);
}
