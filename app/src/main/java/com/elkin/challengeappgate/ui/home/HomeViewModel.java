package com.elkin.challengeappgate.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.data.db.ChallengeDatabase;
import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.data.repository.AttemptsRepositoryImpl;
import com.elkin.challengeappgate.domain.use_case.HomeUseCase;
import com.elkin.challengeappgate.domain.use_case_impl.HomeUseCaseImpl;
import com.elkin.challengeappgate.utils.Resource;
import com.elkin.challengeappgate.utils.UiEvent;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<Integer> userID = new MutableLiveData<>();
    private final MutableLiveData<List<Attempts>> listObjects = new MutableLiveData<>();
    private HomeUseCase homeUseCase;
    private Context context;
    private UiEvent uiEvent;

    public void setContext(Context context, UiEvent uiEvent) {
        this.context = context;
        this.uiEvent = uiEvent;
    }

    void sendUserId(int userId){
        userID.setValue(userId);
        homeUseCase = new HomeUseCaseImpl(new AttemptsRepositoryImpl(ChallengeDatabase.getDataBase(context)));
    }

    public LiveData<Integer> getUser(){
        return userID;
    }
    public LiveData<List<Attempts>> getListAttempts(){
        return listObjects;
    }

    public void getAttempts(int userID){
        homeUseCase.getAttemptsUser(userID, new Resource<List<Attempts>>() {
            @Override
            public void OnError(String message) {uiEvent.showError(message);}

            @Override
            public void OnSuccess(List<Attempts> data) {
                Log.e("getAttempts", ""+data);
                listObjects.postValue(data);
            }

            @Override
            public void OnLoading(Boolean isLoading) {uiEvent.showLoading(isLoading);}
        });
    }
}