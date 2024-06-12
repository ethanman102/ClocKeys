package com.example.clockeys.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clockeys.R;
import com.example.clockeys.databinding.FragmentProfileBinding;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private LinearLayout timeCardButton,infoButton,logoutButton;
    private TextView employeeName,employeeId,employeeBio,employeeHometown,employeeCompany,employeeJobTitle,employeeJobAddress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bindViews(root); // bind the views to the root...

        timeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}