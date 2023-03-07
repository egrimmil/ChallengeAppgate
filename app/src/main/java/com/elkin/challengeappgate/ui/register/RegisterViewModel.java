package com.elkin.challengeappgate.ui.register;

import androidx.lifecycle.ViewModel;

import com.elkin.challengeappgate.utils.UiEvent;

public class RegisterViewModel extends ViewModel {

    private UiEvent events;

    public void setUiEvent(UiEvent events) {
        this.events = events;
    }

    public void registerUser(String user, String pass){
        events.OnLoading(true);

    }
}