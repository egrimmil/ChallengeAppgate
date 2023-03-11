package com.elkin.challengeappgate.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.elkin.commons.utils.UiEvent;

public class BaseFragment extends Fragment implements UiEvent {

    public BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (baseActivity == null) {
            baseActivity = (BaseActivity) getActivity();
        }
    }

    public void showLoading(Boolean isLoading) {
        baseActivity.showLoading(isLoading);
    }

    public void showError(String message) {
        baseActivity.showError(message);
    }

    public void showAlert(String message) {
        baseActivity.showAlert(
                message
        );
    }
}