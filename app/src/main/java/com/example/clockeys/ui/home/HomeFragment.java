package com.example.clockeys.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment implements OnEmployeeFiredCallback {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private List<Employee> employeeList;
    private EmployeeAdapter employeeAdapter;
    private ArrayAdapter<String> sortAdapter;
    private Company company;
    private List<String> sortTypes;
    private AutoCompleteTextView autoCompleteTextView;
    private Button clearSort;
    private TextInputLayout textInputLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<Employee> myEmployees = new ArrayList<Employee>();
        myEmployees.add(new Employee(1092,"Zion Keys",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker","bio","address"));
        myEmployees.add(new Employee(1092,"Addison Keys2",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker2","bio","address"));
        myEmployees.add(new Employee(1092,"Ethan Keys3",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker3","bio","address"));
        myEmployees.add(new Employee(1092,"Brig Keys4",new Date(),new Timecard(new ArrayList<Punch>()),new Date(),"Worker4","bio","address"));

        company = new Company("Resource Bearing",myEmployees.size(),myEmployees,"Image",19289,12);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.employeeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(Boolean.TRUE);

        employeeList = company.getEmployees();
        employeeAdapter = new EmployeeAdapter(getContext(),employeeList,this,company);
        recyclerView.setAdapter(employeeAdapter);


        autoCompleteTextView = root.findViewById(R.id.employeeSorterAutoCompleteTextView);
        sortTypes = Arrays.asList(getResources().getStringArray(R.array.sort_types));
        sortAdapter = new ArrayAdapter<String>(getContext(),R.layout.sort_item,sortTypes);
        autoCompleteTextView.setAdapter(sortAdapter);
        textInputLayout = root.findViewById(R.id.employeeSorterInputLayout);
        clearSort = root.findViewById(R.id.clearSortButton);
        clearSort.setVisibility(View.GONE);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Employee> sortEmployees = sortEmployees(parent.getItemAtPosition(position).toString());
                employeeAdapter.updateList(sortEmployees);
                clearSort.setVisibility(View.VISIBLE);
            }
        });

        clearSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
                clearSort.setVisibility(View.GONE);
                employeeAdapter.updateList(company.getEmployees());
            }
        });


        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<Employee> sortEmployees(String sortType){
        List<Employee> sortedEmployees = new ArrayList<>(company.getEmployees());
        sortedEmployees.sort(new Employee.EmployeeComparator<>(sortType));
        return Collections.unmodifiableList(sortedEmployees);
    }

    @Override
    public boolean onEmployeeFired(Employee employee) {

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        autoCompleteTextView.setText("");
    }

    @Override
    public void onPause() {
        super.onPause();
        autoCompleteTextView.setText("");
    }
}