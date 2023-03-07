package com.elkin.challengeappgate.base;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.elkin.challengeappgate.utils.UiEvent;

import org.json.JSONObject;

public class BaseActivity extends AppCompatActivity implements UiEvent {

    @Override
    public void showLoading(Boolean isLoading) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showAlert(String message) {

    }
}
