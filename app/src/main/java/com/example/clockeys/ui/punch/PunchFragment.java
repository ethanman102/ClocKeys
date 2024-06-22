package com.example.clockeys.ui.punch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.clockeys.databinding.FragmentPunchBinding;

public class PunchFragment extends Fragment {

    private TextView punchType,lastPunchTime,companyName;
    private TextClock textClock;
    private FragmentPunchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PunchViewModel dashboardViewModel =
                new ViewModelProvider(this).get(PunchViewModel.class);

        binding = FragmentPunchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}