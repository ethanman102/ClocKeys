package com.example.clockeys.Startups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clockeys.R;

public class LoginStart extends AppCompatActivity {

    private Button signupButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_start);


        // Get the Login and Signup Buttons...

        signupButton = findViewById(R.id.signupButtonView);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginStart.this, Signup.class);
                startActivity(intent);
            }
        });


    }
}