package com.elkin.domain.use_case;

import com.elkin.commons.utils.Resource;
import com.elkin.domain.models.AttemptModel;

import java.util.List;

public interface HomeUseCase {
    void getAttemptsUser(int userId, Resource<List<AttemptModel>> attempts);
}
