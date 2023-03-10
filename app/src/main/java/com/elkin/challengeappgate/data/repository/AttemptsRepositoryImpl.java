package com.elkin.challengeappgate.data.repository;

import android.os.Handler;
import android.os.Looper;

import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.db.dao.AttemptDao;
import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.domain.repository.AttemptsRepository;
import com.elkin.challengeappgate.utils.Resource;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AttemptsRepositoryImpl implements AttemptsRepository {

    private final AttemptDao attemptDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public AttemptsRepositoryImpl(ChallengeDatabase db){
        this.attemptDao = db.attemptDao();
    }
    @Override
    public void getAttemptsUserId(int userId, Resource<List<Attempts>> callback) {
        callback.OnLoading(true);
        executor.execute(() -> {
            List<Attempts> list = attemptDao.findByUserId(userId);
            handler.post(()->{
                callback.OnLoading(false);
                callback.OnSuccess(list);
            });
        });
    }
}
