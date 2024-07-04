package com.example.clockeys.Startups.UserSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class SignupNameActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Employee employee;
    private Button confirmButton;
    private TextInputEditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_name);
        employee = new Employee();

        bindViews();
        setViews();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = name.getText().toString().trim();
                if (!text.isEmpty()){
                    employee.setName(text);
                    launchNext();
                }
            }
        });
    }


    public void bindViews(){
        toolbar = findViewById(R.id.signupNameToolbar);
        confirmButton = findViewById(R.id.signupNameButton);
        name = findViewById(R.id.signupNameEditText);
    }

    public void setViews(){
        if (employee.getName() != null){
            name.setText(employee.getName());
        }
    }

    public void launchNext(){
        Intent intent = new Intent(SignupNameActivity.this,SignupDateOfBirthActivity.class);
        intent.putExtra("employee",employee);
        startActivity(intent);
    }

}