package com.elkin.challengeappgate.ui.home;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;

import com.elkin.challengeappgate.R;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.elkin.challengeappgate.base.BaseActivity;
import com.elkin.challengeappgate.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HomeViewModel viewModel;
    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        setupActionBarWithNavController(this, navController);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userId = bundle.getString("idUser");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.sendUserId(Integer.parseInt(userId));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}