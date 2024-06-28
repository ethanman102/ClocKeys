package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileInputActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputEditText editText;
    private TextInputLayout inputLayout;
    private TextView description;
    private Employee employee;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_input);

        intent = getIntent();


        bindViews();
    }


    private void bindViews(){
        toolbar = findViewById(R.id.profileInputToolbar);
        editText = findViewById(R.id.profileInputEditText);
        inputLayout = findViewById(R.id.profileInputInputLayout);
        description = findViewById(R.id.profileInputDescription);
    }

    private void setViews(){
        toolbar.setTitle(intent.getStringExtra("hint"));
        editText.setText(intent.getStringExtra("text"));
    }
}