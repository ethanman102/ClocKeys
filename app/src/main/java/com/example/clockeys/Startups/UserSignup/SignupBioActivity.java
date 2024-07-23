package com.example.clockeys.Startups.UserSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.textfield.TextInputEditText;

public class SignupBioActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button confirmButton;
    private TextInputEditText bio;
    private Employee employee;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_bio);

        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);

        bindViews();
        setViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = bio.getText().toString().trim();
                if (text.isEmpty()){
                    employee.setBio("");
                }else{
                    employee.setBio(text);
                }
                launchNext();
            }
        });


    }

    public void bindViews(){
        toolbar = findViewById(R.id.signupBioToolbar);
        confirmButton = findViewById(R.id.signupBioButton);
        bio = findViewById(R.id.signupBioEditText);
        setSupportActionBar(toolbar);
    }

    public void setViews(){
        if (employee.getBio() != null){
            bio.setText(employee.getBio());
        }
    }

    public void launchNext(){
        Intent nextIntent = new Intent(SignupBioActivity.this, SignupPhotoActivity.class);
        nextIntent.putExtra("employee",employee);
        startActivity(nextIntent);
    }
}