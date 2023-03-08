package com.elkin.challengeappgate.data.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.db.dao.UserDao;
import com.elkin.challengeappgate.data.db.entities.User;
import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.repository.UserRepository;
import com.elkin.challengeappgate.utils.Resource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public UserRepositoryImpl(ChallengeDatabase db) {
        this.userDao = db.userDao();
    }

    @Override
    public void registerUser(UserModel userModel, Resource<String> callback) {
        callback.OnLoading(true);
        Log.e("register", userModel.getUserName() + " " + userModel.getUserPass());
        User user = new User();
        user.setName(userModel.getUserName());
        user.setPass(userModel.getUserPass());
        executor.execute(() -> {
            Long resp = userDao.insertAllUsers(user);
            handler.post(() -> {
                Log.e("respInsert", resp.toString());
                callback.OnLoading(false);
                callback.OnSuccess(resp.toString());
            });
        });
    }

    @Override
    public UserModel getUsersId(int id, Resource<UserModel> userModel) {
        return null;
    }
}