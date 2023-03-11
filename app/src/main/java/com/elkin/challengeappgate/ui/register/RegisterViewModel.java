package com.elkin.challengeappgate.ui.register;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.R;
import com.elkin.commons.models.UserModel;
import com.elkin.domain.di.RepositoryImplInstances;
import com.elkin.domain.use_case.RegisterUserUseCase;
import com.elkin.domain.use_case_impl.RegisterUserUseCaseImpl;
import com.elkin.commons.utils.DataUtil;
import com.elkin.commons.utils.Resource;
import com.elkin.commons.utils.UiEvent;

public class RegisterViewModel extends ViewModel {

    private UiEvent uiEvent;
    private RegisterUserUseCase userUseCase;
    MutableLiveData<Integer> error = new MutableLiveData<>();

    public void setParams(UiEvent events) {
        this.uiEvent = events;
    }
    public void setRegisterUseCase(Context context){
        //userUseCase = new RegisterUserUseCaseImpl(new UserRepositoryImpl(ChallengeDatabase.getDataBase(context)));
        userUseCase = new RegisterUserUseCaseImpl(RepositoryImplInstances.getUserRepositoryImpl(context));
    }

    public LiveData<Integer> getError(){
        return error;
    }

    public void registerUser(String user, String pass) {
        if (!user.equals("") && !pass.equals("")) {
            if (DataUtil.isValidEmail(user)) {
                if (DataUtil.isValidPass(pass)) {
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
                } else {
                    error.setValue(R.string.regis_error_pass);
                }
            } else {
                error.setValue(R.string.regis_error_email);
            }
        } else {
            error.setValue(R.string.regis_error_empty);
        }
    }
}