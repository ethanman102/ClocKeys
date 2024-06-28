package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;


public class EditProfileActivity extends AppCompatActivity {

    private Employee employee;
    private Toolbar toolbar;
    private RelativeLayout name,birthdate,address,bio;
    private TextView leaveCompany,deleteProfile;

    private ActivityResultLauncher<Intent> profileInputActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileInputActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                employee = intent.getSerializableExtra("employee", Employee.class);
            }

        });

        bindViews();
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditName();
            }
        });



    }
    private void launchEditName(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);

        intent.putExtra("employee",employee);
        intent.putExtra("hint",getString(R.string.name));
        intent.putExtra("description",getString(R.string.name_description));
        intent.putExtra("length",30);

        profileInputActivityResultLauncher.launch(intent);

    }
    private void bindViews(){

        // binding the layouts

        name = findViewById(R.id.nameLayout);
        bio = findViewById(R.id.bioLayout);
        birthdate = findViewById(R.id.birthdayLayout);
        address = findViewById(R.id.addressLayout);

    }
}