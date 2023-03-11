package com.elkin.domain.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.elkin.commons.utils.Resource;
import com.elkin.data.db.ChallengeDatabase;
import com.elkin.data.db.dao.AttemptDao;
import com.elkin.data.db.entities.Attempts;
import com.elkin.domain.mappers.AttemptMapper;
import com.elkin.domain.models.AttemptModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AttemptsRepositoryImpl implements AttemptsRepository {

    private final AttemptDao attemptDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public AttemptsRepositoryImpl(Context context){
        this.attemptDao = ChallengeDatabase.getDataBase(context).attemptDao();
    }

    @Override
    public void getAttemptsUserId(int userId, Resource<List<AttemptModel>> callback) {
        callback.OnLoading(true);
        executor.execute(()->{
            List<Attempts> list = attemptDao.findByUserId(userId);
            AttemptMapper attemptConverter = new AttemptMapper();
            List<AttemptModel> listAttemptModel = new ArrayList<>();
            for (Attempts attempts : list) {
                listAttemptModel.add(attemptConverter.fromEntity(attempts));
            }
            handler.post(()->{
                callback.OnLoading(false);
                callback.OnSuccess(listAttemptModel);
            });
        });
    }

    /*@Override
    public List<Attempts> getAttemptsUserId(int userId) {
        MutableLiveData<List<Attempts>> listAttempts = new MutableLiveData<>();
        executor.execute(()->{
            List<Attempts> list = attemptDao.findByUserId(userId);
            handler.post(()->{
                listAttempts.setValue(list);
            });
        });
        return listAttempts.getValue();
    }*/

}