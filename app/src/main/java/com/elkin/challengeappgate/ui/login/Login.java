package com.elkin.challengeappgate.ui.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.elkin.challengeappgate.R;
import com.elkin.challengeappgate.base.BaseActivity;
import com.elkin.challengeappgate.databinding.ActivityLoginBinding;
import com.elkin.challengeappgate.ui.home.MainActivity;
import com.elkin.challengeappgate.ui.register.Register;
import com.elkin.challengeappgate.ui.register.RegisterViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class Login extends BaseActivity implements LocationListener {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private final int codePermission = 900;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.setParamsInit(this, getApplicationContext());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkPermissions();
        clickListeners();
    }

    private void clickListeners() {
        binding.btnLogin.setOnClickListener(view -> {
            String user = Objects.requireNonNull(binding.tfEtUserLogin.getText()).toString().trim();
            String pass = Objects.requireNonNull(binding.tfEtPassLogin.getText()).toString().trim();
            viewModel.validateUser(user, pass);
        });

        binding.btnRegisLogin.setOnClickListener(view -> {
            onNavigate(this, Register.class);
        });
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    codePermission
            );
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == codePermission) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                checkPermissions();
            } else {
                Toast.makeText(this, getString(R.string.log_request_location_denied), Toast.LENGTH_LONG).show();
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )) {
                    startActivity(
                            new Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", this.getPackageName(), null)
                                    )
                            );
                }
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        viewModel.setLocation(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void showAlert(String message) {
        super.showAlert(message);
        Log.e("loginCorrect", message);
        Bundle bundle = new Bundle();
        bundle.putString("idUser", message);
        onNavigateExtra(this, MainActivity.class, bundle);
    }
}