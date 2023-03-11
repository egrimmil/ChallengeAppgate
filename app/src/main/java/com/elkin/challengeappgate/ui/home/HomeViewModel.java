package com.elkin.challengeappgate.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elkin.commons.utils.Resource;
import com.elkin.domain.di.RepositoryImplInstances;
import com.elkin.domain.models.AttemptModel;
import com.elkin.domain.repository.AttemptsRepositoryImpl;
import com.elkin.domain.use_case.HomeUseCase;
import com.elkin.domain.use_case_impl.HomeUseCaseImpl;
import com.elkin.commons.utils.UiEvent;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<Integer> userID = new MutableLiveData<>();
    private final MutableLiveData<List<AttemptModel>> listObjects = new MutableLiveData<>();
    private HomeUseCase homeUseCase;
    private UiEvent uiEvent;

    public void setParams(UiEvent uiEvent) {
        this.uiEvent = uiEvent;
    }

    void sendUserId(int userId){
        userID.setValue(userId);
    }

    public void setHomeUseCase(Context context){
        homeUseCase = new HomeUseCaseImpl(RepositoryImplInstances.getAttemptRepositoryImpl(context));
    }

    public LiveData<Integer> getUser(){
        return userID;
    }
    public LiveData<List<AttemptModel>> getListAttempts(){
        return listObjects;
    }

    public void getAttempts(int userID){
        homeUseCase.getAttemptsUser(userID, new Resource<List<AttemptModel>>() {
            @Override
            public void OnError(String message) {uiEvent.showError(message);}

            @Override
            public void OnSuccess(List<AttemptModel> data) {
                Log.e("getAttempts", ""+data);
                listObjects.postValue(data);
            }

            @Override
            public void OnLoading(Boolean isLoading) {uiEvent.showLoading(isLoading);}
        });
    }
}