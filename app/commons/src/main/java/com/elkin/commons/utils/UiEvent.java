package com.elkin.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public interface UiEvent {
    void showLoading(Boolean isLoading);
    void showError(String message);
    void showAlert(String message);
    default void onNavigate(Context context, Class activity){
        context.startActivity(new Intent(context, activity));
    }
    default void onNavigateExtra(Context context, Class activity, Bundle bundle){
        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}