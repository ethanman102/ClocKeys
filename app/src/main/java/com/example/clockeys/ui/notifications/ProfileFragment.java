package com.example.clockeys.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clockeys.MainActivity;
import com.example.clockeys.Management.TimecardActivity;
import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;
import com.example.clockeys.databinding.FragmentProfileBinding;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ProfileFragment extends Fragment {

    private Employee user;
    private FragmentProfileBinding binding;
    private LinearLayout timeCardButton,infoButton,logoutButton;
    private TextView employeeName,employeeId,employeeBio,employeeHometown,employeeCompany,employeeJobTitle,employeeJobAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<Punch> punches = new ArrayList<Punch>();
        punches.add(new Punch(LocalDateTime.of(2024, 6, 16, 22, 56)));
        Timecard tc = new Timecard(punches);
        bindViews(root); // bind the views to the root...
        user = new Employee(1092,"Ethan Keys",new Date(),tc,new Date(),"Worker");

        timeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeCardActivity();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return root;
    }

    private void bindViews(View root){

        // Get Linear Layouts

        timeCardButton = root.findViewById(R.id.employeeProfileTimecardLinearLayout);
        infoButton = root.findViewById(R.id.employeeProfileInfoLinearLayout);
        logoutButton = root.findViewById(R.id.employeeProfileLogoutLinearLayout);

        // Get TextViews

        employeeName = root.findViewById(R.id.profileEmployeeName);
        employeeId = root.findViewById(R.id.profileEmployeeNumber);
        employeeBio = root.findViewById(R.id.profileEmployeeBio);
        employeeHometown = root.findViewById(R.id.profileEmployeeHometown);
        employeeCompany = root.findViewById(R.id.employeeProfileJobCompany);
        employeeJobAddress = root.findViewById(R.id.employeeProfileJobAddress);
        employeeJobTitle = root.findViewById(R.id.employeeProfileJobTitle);
    }

    private void startTimeCardActivity(){
        Intent intent = new Intent(getActivity(), TimecardActivity.class);
        intent.putExtra("employee",(Serializable) user);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}