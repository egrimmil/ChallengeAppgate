package com.elkin.challengeappgate.base;

import androidx.appcompat.app.AppCompatActivity;

import com.elkin.challengeappgate.utils.UiEvent;

import org.json.JSONObject;

public class BaseActivity extends AppCompatActivity implements UiEvent {

    @Override
    public void OnError(String message) {

    }

    @Override
    public void OnSuccess(JSONObject data) {}

    @Override
    public void OnLoading(Boolean isLoading) {

    }
}
