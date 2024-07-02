package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class EditProfileActivity extends AppCompatActivity {

    private Employee employee;
    private Toolbar toolbar;
    private RelativeLayout name,birthdate,address,bio;
    private TextView leaveCompany,deleteProfile,nameTV,bioTV,addressTV;

    private ActivityResultLauncher<Intent> profileInputActivityResultLauncher;
    private Intent intent,updatedIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        updatedIntent = new Intent();

        employee = getIntent().getSerializableExtra("employee",Employee.class);
        
        profileInputActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                employee = intent.getSerializableExtra("employee", Employee.class);
                setViews();
            }

        });

        bindViews();
        setViews();
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditName();
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditBio();
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditAddress();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,updatedIntent);
                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.confirmButton){
            updatedIntent.putExtra("employee",(Serializable) employee);
            setResult(RESULT_OK,updatedIntent);
        }
        updatedIntent.putExtra("employee",(Serializable) employee);
        setResult(RESULT_OK,updatedIntent);

        finish();
        return Boolean.TRUE;
    }
    public void launchEditName(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);

        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getName());
        intent.putExtra("hint",getString(R.string.name));
        intent.putExtra("description",getString(R.string.name_description));
        intent.putExtra("length",30);

        profileInputActivityResultLauncher.launch(intent);
    }

    public void launchEditBio(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);
        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getBio());
        intent.putExtra("hint",getString(R.string.bio));
        intent.putExtra("description",getString(R.string.bio_description));
        intent.putExtra("length",150);

        profileInputActivityResultLauncher.launch(intent);
    }

    public void launchEditAddress(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);
        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getAddress());
        intent.putExtra("hint",getString(R.string.address));
        intent.putExtra("description",getString(R.string.address_description));
        intent.putExtra("length",40);

        profileInputActivityResultLauncher.launch(intent);

    }

    public void launchEditBirthday(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);
        intent.putExtra("employee",employee);

        profileInputActivityResultLauncher.launch(intent);
    }
    private void bindViews(){

        // binding the layouts

        name = findViewById(R.id.nameLayout);
        bio = findViewById(R.id.bioLayout);
        birthdate = findViewById(R.id.birthdayLayout);
        address = findViewById(R.id.addressLayout);

        bioTV = findViewById(R.id.employeeBio);
        nameTV = findViewById(R.id.employeeNameChangeable);
        addressTV = findViewById(R.id.employeeAddress);

    }

    private void setViews(){
        nameTV.setText(employee.getName());
        bioTV.setText(employee.getBio());
        addressTV.setText(employee.getAddress());
    }
}