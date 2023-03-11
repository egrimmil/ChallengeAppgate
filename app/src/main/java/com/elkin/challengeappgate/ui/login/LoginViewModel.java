package com.elkin.challengeappgate.ui.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.R;
import com.elkin.commons.models.UserModel;
import com.elkin.domain.di.RepositoryImplInstances;
import com.elkin.domain.use_case.LoginUseCase;
import com.elkin.domain.use_case_impl.LoginUseCaseImpl;
import com.elkin.commons.utils.DataUtil;
import com.elkin.commons.utils.Resource;
import com.elkin.commons.utils.UiEvent;

public class LoginViewModel extends ViewModel {
    private UiEvent uiEvent;
    private String latitude;
    private String longitude;
    private LoginUseCase loginUseCase;
    MutableLiveData<Integer> error = new MutableLiveData<>();

    public void setParamsInit(UiEvent events) {
        this.uiEvent = events;
    }

    public void setLoginUseCase(Context context){
        loginUseCase = new LoginUseCaseImpl(RepositoryImplInstances.getUserRepositoryImpl(context));
    }

    public LiveData<Integer> getError(){
        return error;
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
                    error.setValue(R.string.regis_desc_pass);
                }
            } else {
                error.setValue(R.string.regis_error_email);
            }
        } else {
            error.setValue(R.string.regis_error_empty);
        }
    }
}
