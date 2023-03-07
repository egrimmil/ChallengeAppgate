package com.elkin.challengeappgate.ui.register;

import android.net.DnsResolver;
import android.os.Handler;

import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.data.repository.UserRepositoryImpl;
import com.elkin.challengeappgate.domain.models.UserModel;
import com.elkin.challengeappgate.domain.use_case.RegisterUserUseCase;
import com.elkin.challengeappgate.domain.use_case_impl.RegisterUserUseCaseImpl;
import com.elkin.challengeappgate.utils.Resource;
import com.elkin.challengeappgate.utils.UiEvent;

public class RegisterViewModel extends ViewModel {

    private UiEvent uiEvent;
    private final RegisterUserUseCase userUseCase = new RegisterUserUseCaseImpl(new UserRepositoryImpl());

    public void setUiEvent(UiEvent events) {
        this.uiEvent = events;
    }

    public void registerUser(String user, String pass) {
        userUseCase.registerUser(new UserModel(user, pass), new Resource<String>() {
            @Override
            public void OnError(String message) {
                uiEvent.showError(message);
            }

            @Override
            public void OnSuccess(String data) {
                uiEvent.showAlert("Ya puedes ingresar a la app.");
            }

            @Override
            public void OnLoading(Boolean isLoading) {
                uiEvent.showLoading(isLoading);
            }
        });
    }
}