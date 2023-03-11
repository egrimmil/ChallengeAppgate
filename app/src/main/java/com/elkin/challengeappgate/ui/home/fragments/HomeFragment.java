package com.elkin.challengeappgate.ui.home.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkin.challengeappgate.base.BaseFragment;
import com.elkin.challengeappgate.databinding.FragmentHomeBinding;
import com.elkin.challengeappgate.ui.home.HomeViewModel;
import com.elkin.challengeappgate.ui.home.adapters.HomeAdapter;

public class HomeFragment extends BaseFragment{

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private final HomeAdapter adapter = new HomeAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.setParams(this);
        viewModel.setHomeUseCase(requireActivity());
        binding.rvAttemptsHome.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        listenerObservers();
    }

    private void listenerObservers(){
        viewModel.getUser().observe(getViewLifecycleOwner(), userId -> {
            viewModel.getAttempts(userId);
        });
        viewModel.getListAttempts().observe(getViewLifecycleOwner(), listAttempts ->{
            if(!listAttempts.isEmpty()) {
                adapter.loadAttempts(listAttempts);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}