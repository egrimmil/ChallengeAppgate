package com.elkin.challengeappgate.ui.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.elkin.challengeappgate.R;
import com.elkin.challengeappgate.data.db.entities.Attempts;
import com.elkin.challengeappgate.databinding.HolderHomeBinding;
import com.elkin.challengeappgate.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HolderHome> {

    private List<Attempts> attemptsList = new ArrayList<>();

    @NonNull
    @Override
    public HolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderHome(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHome holder, int position) {
        holder.onBind(attemptsList.get(position));
    }

    @Override
    public int getItemCount() {
        return attemptsList.size();
    }

    public void loadAttempts(List<Attempts> attempts) {
        this.attemptsList = attempts;
        notifyAll();
    }

    public static class HolderHome extends RecyclerView.ViewHolder {

        private final HolderHomeBinding binding;

        public HolderHome(@NonNull View itemView) {
            super(itemView);
            binding = HolderHomeBinding.bind(itemView);
        }

        public void onBind(Attempts attempts) {
            binding.lbDateHolderHome.setText(DataUtil.dateFormatToFormat(attempts.getDate(), "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS", "dd/MM/yyyy"));
            binding.lbOperationHolderHome.setText(attempts.getResult());
            if (attempts.getResult().toLowerCase(Locale.ROOT).equals("denegado")) {
                binding.lbOperationHolderHome.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.red));
            } else {
                binding.lbOperationHolderHome.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.green));
            }
        }
    }
}