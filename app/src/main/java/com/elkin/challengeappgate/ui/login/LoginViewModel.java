package com.elkin.challengeappgate.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.R;
import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.repository.UserRepositoryImpl;
import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.use_case.LoginUseCase;
import com.elkin.challengeappgate.domain.use_case_impl.LoginUseCaseImpl;
import com.elkin.challengeappgate.ui.home.MainActivity;
import com.elkin.challengeappgate.utils.DataUtil;
import com.elkin.challengeappgate.utils.Resource;
import com.elkin.challengeappgate.utils.UiEvent;

public class LoginViewModel extends ViewModel {
    private UiEvent uiEvent;
    private Context context;
    private String latitude;
    private String longitude;
    private LoginUseCase loginUseCase;

    public void setParamsInit(UiEvent events, Context context) {
        this.uiEvent = events;
        this.context = context;
        loginUseCase = new LoginUseCaseImpl(new UserRepositoryImpl(ChallengeDatabase.getDataBase(context)));
    }

    public void setLocation(double latitude, double longitude) {
        this.longitude = String.valueOf(longitude);
        this.latitude = String.valueOf(latitude);
    }

    public void validateUser(String user, String pass) {
        if (!user.equals("") && !pass.equals("")) {
            if (DataUtil.isValidEmail(user)) {
                if (DataUtil.isValidPass(pass)) {
                    Log.e("location", "lat: " + latitude + " long: " + longitude);
                    loginUseCase.loginUser(new UserModel(user, pass), latitude, longitude, new Resource<String>(){

                        @Override
                        public void OnError(String message) {uiEvent.showError(message);}

                        @Override
                        public void OnSuccess(String data) {
                            Log.e("loginVM", data);
                            uiEvent.showAlert(data);
                        }

                        @Override
                        public void OnLoading(Boolean isLoading) {uiEvent.showLoading(isLoading);}
                    });

                } else {
                    uiEvent.showError(context.getString(R.string.regis_desc_pass));
                }
            } else {
                uiEvent.showError(context.getString(R.string.regis_error_email));
            }
        } else {
            uiEvent.showError(context.getString(R.string.regis_error_empty));
        }
    }
}
