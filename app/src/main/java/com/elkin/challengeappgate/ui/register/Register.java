package com.elkin.challengeappgate.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.elkin.challengeappgate.R;
import com.elkin.challengeappgate.base.BaseActivity;
import com.elkin.challengeappgate.databinding.ActivityRegisterBinding;
import com.elkin.challengeappgate.ui.login.Login;
import com.elkin.challengeappgate.utils.UiEvent;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.Objects;

public class Register extends BaseActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        viewModel.setParams(this, getApplicationContext());
        clickListeners();
    }

    private void clickListeners() {
        binding.btnRegister.setOnClickListener(view -> {
            String name = Objects.requireNonNull(binding.tfEtUserRegister.getText()).toString().trim();
            String pass = Objects.requireNonNull(binding.tfEtPassRegister.getText()).toString().trim();
            if (!name.equals("") && !pass.equals("")) {
                viewModel.registerUser(name, pass);
            } else {
                showError(getString(R.string.regis_error_empty));
            }
        });
    }

    @Override
    public void showAlert(String message) {
        super.showAlert(message);
        Snackbar.make(findViewById(R.id.contentLayout), message, BaseTransientBottomBar.LENGTH_LONG).show();
        onNavigate(this, Login.class);
    }
}