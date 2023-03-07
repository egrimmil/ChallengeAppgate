package com.elkin.challengeappgate.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elkin.challengeappgate.base.BaseActivity;
import com.elkin.challengeappgate.databinding.ActivityLoginBinding;
import com.elkin.challengeappgate.ui.register.Register;

public class Login extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clickListeners();
    }

    private void clickListeners() {
        binding.btnLogin.setOnClickListener(view -> {

        });

        binding.btnRegisLogin.setOnClickListener(view -> {
            onNavigate(this, Register.class);
        });
    }
}