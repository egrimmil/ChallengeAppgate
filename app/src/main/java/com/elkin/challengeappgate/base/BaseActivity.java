package com.elkin.challengeappgate.base;

import androidx.appcompat.app.AppCompatActivity;

import com.elkin.challengeappgate.R;
import com.elkin.challengeappgate.utils.UiEvent;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity implements UiEvent {

    @Override
    public void showLoading(Boolean isLoading) {
        Snackbar snack = Snackbar.make(findViewById(R.id.contentLayout), getString(R.string.description_loading), BaseTransientBottomBar.LENGTH_LONG);
        if(isLoading){
            snack.show();
        }else{
            snack.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.contentLayout), message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showAlert(String message) {
        Snackbar.make(findViewById(R.id.contentLayout), message, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}