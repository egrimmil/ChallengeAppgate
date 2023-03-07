package com.elkin.challengeappgate.utils;

import android.content.Context;
import android.content.Intent;

public interface UiEvent {
    void showLoading(Boolean isLoading);
    void showError(String message);
    void showAlert(String message);
    default void onNavigate(Context context, Class activity){
        context.startActivity(new Intent(context, activity));
    }
}
