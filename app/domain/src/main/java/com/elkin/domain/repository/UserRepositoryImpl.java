package com.elkin.domain.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.elkin.data.db.ChallengeDatabase;
import com.elkin.data.db.dao.AttemptDao;
import com.elkin.data.db.dao.UserDao;
import com.elkin.data.db.entities.Attempts;
import com.elkin.data.db.entities.User;
import com.elkin.data.remote.dto.TimeZoneDto;
import com.elkin.data.remote.service.HttpService;
import com.elkin.commons.models.UserModel;
import com.elkin.commons.utils.Resource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final AttemptDao attemptDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private Resource<String> callbackRepo;
    private final HttpService apiService;
    private final Handler handler = new Handler(Looper.getMainLooper());

    public UserRepositoryImpl(Context context) {
        this.userDao = ChallengeDatabase.getDataBase(context).userDao();
        this.attemptDao = ChallengeDatabase.getDataBase(context).attemptDao();
        this.apiService = new HttpService();
    }

    @Override
    public void registerUser(UserModel userModel, Resource<String> callback) {
        this.callbackRepo = callback;
        callbackRepo.OnLoading(true);
        User user = new User();
        user.setName(userModel.getUserEmail());
        user.setPass(userModel.getUserPass());
        executor.execute(() -> {
            Long resp = userDao.insertAllUsers(user);
            handler.post(() -> {
                callbackRepo.OnLoading(false);
                callbackRepo.OnSuccess(resp.toString());
            });
        });
    }

    @Override
    public void getUserByEmailPass(UserModel userModel, String latitude, String longitude, Resource<String> callback) {
        this.callbackRepo = callback;
        callbackRepo.OnLoading(true);
        executor.execute(() -> {
            User user = userDao.findByName(userModel.getUserEmail());
            if (user != null) {
                Log.e("userDao", "id: " + user.getUid() + "email: " + user.getName());
                Attempts attempts = new Attempts();
                attempts.setUserId(user.getUid());
                if (!Objects.equals(user.getPass(), userModel.getUserPass())) {
                    TimeZoneDto time = getTimeZone(latitude, longitude);
                    attempts.setDate(time.getCurrentLocalTime());
                    attempts.setResult("Denegado");
                    attemptDao.insertAttempt(attempts);
                    callbackRepo.OnLoading(false);
                    handler.post(() -> callbackRepo.OnError("La contraseña es incorrecta"));
                } else {
                    TimeZoneDto time = getTimeZone(latitude, longitude);
                    attempts.setDate(time.getCurrentLocalTime());
                    attempts.setResult("Exitoso");
                    Long aid = attemptDao.insertAttempt(attempts);
                    handler.post(() -> {
                        Log.e("attemptDao", "id: " + aid);
                        callbackRepo.OnSuccess(String.valueOf(user.getUid()));
                    });
                }
            } else {
                callbackRepo.OnLoading(false);
                callbackRepo.OnError("El usuario no se encuentra registrado");
            }
        });
    }

    private TimeZoneDto getTimeZone(String latitude, String longitude) {
        String uri = "https://www.timeapi.io/api/TimeZone/coordinate?latitude=" + latitude + "&longitude=" + longitude;
        TimeZoneDto timeZone = new TimeZoneDto();
        Message  message = apiService.requestGet(uri);

        if (message.getData().getString("error").equals("true")) {
            String response = message.getData().getString("body");
            callbackRepo.OnError(response);
        } else {
            String response = message.getData().getString("body");
            Log.e("respGet", response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                timeZone = new TimeZoneDto();
                timeZone.setTimeZone(jsonObject.getString("timeZone"));
                timeZone.setCurrentLocalTime(jsonObject.getString("currentLocalTime"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            Log.e("respGetDto", "" + timeZone);
            Log.e("respGetDto2", "timeZone: " + timeZone.getTimeZone() + " localTime: " + timeZone.getCurrentLocalTime());
        }
        return timeZone;
    }
}