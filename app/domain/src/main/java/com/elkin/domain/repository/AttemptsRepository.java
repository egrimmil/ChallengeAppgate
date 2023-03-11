package com.elkin.domain.repository;

import com.elkin.data.db.entities.Attempts;
import com.elkin.commons.utils.Resource;
import com.elkin.domain.models.AttemptModel;

import java.util.List;

public interface AttemptsRepository {
    void getAttemptsUserId(int userId, Resource<List<AttemptModel>> callback);
}
