package com.example.clockeys.ui.punch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;
import com.example.clockeys.databinding.FragmentPunchBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PunchFragment extends Fragment {

    private TextView punchType,lastPunchTime,companyName;
    private TextClock textClock;
    private Button punchButton;
    private Employee employee;
    private Company company;
    private FragmentPunchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PunchViewModel dashboardViewModel =
                new ViewModelProvider(this).get(PunchViewModel.class);

        binding = FragmentPunchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        bindViews(root);

        employee = new Employee(1092,"Ethan Keys",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker");

        punchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime punch = LocalDateTime.now();
                ZonedDateTime zonedDateTime = punch.atZone(ZoneId.systemDefault());
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy h:mm a", Locale.getDefault());
                lastPunchTime.setText(sdf.format(Date.from(zonedDateTime.toInstant())));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void bindViews(View root){

        punchType = root.findViewById(R.id.punchType);
        lastPunchTime = root.findViewById(R.id.lastPunchTV);
        companyName = root.findViewById(R.id.punchFragmentCompanyName);

        textClock = root.findViewById(R.id.punchTextClock);
        textClock.setFormat12Hour("hh:mm:ss a");

        punchButton = root.findViewById(R.id.punchButton);


    }
}