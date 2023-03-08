package com.elkin.challengeappgate.ui.register;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.repository.UserRepositoryImpl;
import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.use_case.RegisterUserUseCase;
import com.elkin.challengeappgate.domain.use_case_impl.RegisterUserUseCaseImpl;
import com.elkin.challengeappgate.utils.DataUtil;
import com.elkin.challengeappgate.utils.Resource;
import com.elkin.challengeappgate.utils.UiEvent;

public class RegisterViewModel extends ViewModel {

    private UiEvent uiEvent;
    private RegisterUserUseCase userUseCase;

    public void setParams(UiEvent events, Context context) {
        this.uiEvent = events;
        userUseCase = new RegisterUserUseCaseImpl(new UserRepositoryImpl(ChallengeDatabase.getDataBase(context)));
    }

    public void registerUser(String user, String pass) {
        if(DataUtil.isValidEmail(user)){
            if (DataUtil.isValidPass(pass)){
                userUseCase.registerUser(new UserModel(user, pass), new Resource<String>() {
                    @Override
                    public void OnError(String message) {
                        uiEvent.showError(message);
                    }

                    @Override
                    public void OnSuccess(String data) {
                        Log.e("insertVM", data);
                        uiEvent.showAlert("Ya puedes ingresar a la app.");
                    }

                    @Override
                    public void OnLoading(Boolean isLoading) {
                        uiEvent.showLoading(isLoading);
                    }
                });
            }else{
                uiEvent.showError("Debes ingresar una contraseña valida");
            }
        }else{
            uiEvent.showError("Debes ingresar un email valido");
        }
    }
}