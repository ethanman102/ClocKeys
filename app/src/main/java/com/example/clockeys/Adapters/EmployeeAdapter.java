package com.example.clockeys.Adapters;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockeys.Callbacks.OnEmployeeFiredCallback;
import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{

    private Context context;
    private List<Employee> employeeList;
    private SparseBooleanArray expandedItems;
    private OnEmployeeFiredCallback callback;
    private Company company;

    public EmployeeAdapter(Context context, List<Employee> employeeList, OnEmployeeFiredCallback callback, Company company) {
        this.context = context;
        this.employeeList = employeeList;
        this.expandedItems = new SparseBooleanArray();
        this.callback = callback;
        this.company = company;
    }

    public void updateList(List<Employee> updated){
        this.employeeList = updated;
        expandedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false);
        return new MyViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvEmployeeName.setText(employee.getName());
        holder.tvEmployeeTitle.setText(employee.getTitle());

        // Setting the visibility for the expansion views for more employee information.

        holder.expandedEmployeeLayout.setVisibility(expandedItems.get(position) ? View.VISIBLE : View.GONE);
        holder.employeeVisibility.setVisibility(!expandedItems.get(position) ? View.VISIBLE : View.GONE);
        holder.employeeInvisibility.setVisibility(expandedItems.get(position) ? View.VISIBLE : View.GONE);



    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvEmployeeName, tvEmployeeTitle;
        private CircleImageView employeeImage;
        private ImageView employeeVisibility;
        private ImageView employeeInvisibility;
        private RelativeLayout expandedEmployeeLayout;
        private EmployeeAdapter adapter;
        public MyViewHolder(@NonNull View itemView, EmployeeAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tvEmployeeName = itemView.findViewById(R.id.employeeLayoutName);
            tvEmployeeTitle = itemView.findViewById(R.id.employeeLayoutTitle);
            employeeImage = itemView.findViewById(R.id.employeeImage);
            employeeVisibility = itemView.findViewById(R.id.employeeVisibilityIcon);
            employeeInvisibility = itemView.findViewById(R.id.employeeInvisibilityIcon);

            expandedEmployeeLayout = itemView.findViewById(R.id.expandedEmployeeLayout);


            // On click listeners for invisibility and visiibility icons...
            employeeVisibility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.expandedItems.put(getAdapterPosition(),!adapter.expandedItems.get(getAdapterPosition()));
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });

            employeeInvisibility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.expandedItems.put(getAdapterPosition(),!adapter.expandedItems.get(getAdapterPosition()));
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });

            employeeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean employeeFired = adapter.callback.onEmployeeFired(adapter.employeeList.get(getAdapterPosition()));
                    if (employeeFired){
                        adapter.company.fireEmployee(adapter.employeeList.get(getAdapterPosition()));
                        adapter.employeeList = adapter.company.getEmployees();
                        adapter.notifyDataSetChanged();
                    }else{
                        Log.d("GTA", "NOT REMOVED... ");
                    }
                }
            });


        }
    }
}
