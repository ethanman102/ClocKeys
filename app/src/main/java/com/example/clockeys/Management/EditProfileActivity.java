package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clockeys.R;


public class EditProfileActivity extends AppCompatActivity {

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
                switch (intent.getIntExtra("editType",0)){

                }
            }




        });



    }

    private void bindViews(){

        // binding the layouts

        name = findViewById(R.id.nameLayout);
        bio = findViewById(R.id.bioLayout);
        birthdate = findViewById(R.id.birthdayLayout);
        address = findViewById(R.id.addressLayout);

    }
}