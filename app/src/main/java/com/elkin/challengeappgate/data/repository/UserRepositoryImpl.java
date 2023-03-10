package com.elkin.challengeappgate.data.repository;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.db.dao.AttemptDao;
import com.elkin.challengeappgate.data.db.dao.UserDao;
import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.data.db.entities.User;
import com.elkin.challengeappgate.data.remote.dto.TimeZoneDto;
import com.elkin.challengeappgate.data.remote.service.HttpService;
import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.repository.UserRepository;
import com.elkin.challengeappgate.utils.Resource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final AttemptDao attemptDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private Resource<String> callbackRepo;
    private final HttpService apiService;
    private final Handler handler = new Handler(Looper.getMainLooper());

    public UserRepositoryImpl(ChallengeDatabase db) {
        this.userDao = db.userDao();
        this.attemptDao = db.attemptDao();
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
                    callbackRepo.OnLoading(false);
                    TimeZoneDto time = getTimeZone(latitude, longitude);
                    attempts.setDate(time.getCurrentLocalTime());
                    attempts.setResult("Denegado");
                    attemptDao.insertAttempt(attempts);
                    handler.post(() -> {
                        callbackRepo.OnError("La contraseña es incorrecta");
                    });
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