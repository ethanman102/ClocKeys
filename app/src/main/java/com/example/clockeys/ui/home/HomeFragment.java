package com.example.clockeys.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockeys.Adapters.EmployeeAdapter;
import com.example.clockeys.Callbacks.OnEmployeeFiredCallback;
import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;
import com.example.clockeys.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements OnEmployeeFiredCallback {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private List<Employee> employeeList;
    private EmployeeAdapter employeeAdapter;
    private Company company;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<Employee> myEmployees = new ArrayList<Employee>();
        myEmployees.add(new Employee(1092,"Ethan Keys",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker"));
        myEmployees.add(new Employee(1092,"Ethan Keys2",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker2"));
        myEmployees.add(new Employee(1092,"Ethan Keys3",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker3"));
        myEmployees.add(new Employee(1092,"Ethan Keys4",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker4"));

        company = new Company("Resource Bearing",myEmployees.size(),myEmployees,"Image",19289,12);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.employeeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(Boolean.TRUE);

        employeeList = company.getEmployees();
        employeeAdapter = new EmployeeAdapter(getContext(),employeeList,this,company);
        recyclerView.setAdapter(employeeAdapter);





        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onEmployeeFired(Employee employee) {
        return false;
    }
}