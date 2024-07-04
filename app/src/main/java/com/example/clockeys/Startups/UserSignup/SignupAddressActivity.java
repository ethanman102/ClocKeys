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

public class SignupAddressActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button confirmButton;
    private TextInputEditText address;
    private Employee employee;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_address);

        employee = getIntent().getSerializableExtra("employee", Employee.class);

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
                String text = address.getText().toString().trim();
                if (!text.isEmpty()){
                    employee.setAddress(text);
                    launchNext();
                }
            }
        });


    }

    public void bindViews(){
        toolbar = findViewById(R.id.signupAddressToolbar);
        confirmButton = findViewById(R.id.signupAddressButton);
        address = findViewById(R.id.signupAddressEditText);
        setSupportActionBar(toolbar);
    }

    public void setViews(){

        if (employee.getAddress() != null){
            address.setText(employee.getAddress());
        }

    }

    public void launchNext(){
        Intent nextIntent = new Intent(SignupAddressActivity.this, SignupBioActivity.class);
        nextIntent.putExtra("employee",employee);
        startActivity(nextIntent);
    }

}