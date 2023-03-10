package com.elkin.challengeappgate.domain.use_case;

import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.utils.Resource;

import java.util.List;

public interface HomeUseCase {
    void getAttemptsUser(int userId, Resource<List<Attempts>> attempts);
}
