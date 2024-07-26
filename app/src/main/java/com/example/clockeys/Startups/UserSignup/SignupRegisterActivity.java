package com.example.clockeys.Startups.UserSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupRegisterActivity extends AppCompatActivity {

    private Employee employee;
    private String filePath;
    private Intent intent;
    private Button confirm;
    private TextInputEditText email,password;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private View viewTint;
    private TextInputLayout emailLayout,passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_register);

        bindViews();

        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);
        mAuth = FirebaseAuth.getInstance();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = String.valueOf(email.getText());
                String passwordText = String.valueOf(password.getText());
                progressBar.setVisibility(View.VISIBLE);
                viewTint.setVisibility(View.VISIBLE);
                setUnclickable();

                if(TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(emailText)) return;

                mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    progressBar.setVisibility(View.GONE);
                                    viewTint.setVisibility(View.GONE);
                                    setClickable();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignupRegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    viewTint.setVisibility(View.GONE);
                                    setClickable();
                                }
                            }
                        });
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void bindViews(){
        confirm = findViewById(R.id.signupRegisterButton);
        email = findViewById(R.id.signupEmailEditText);
        emailLayout = findViewById(R.id.signupEmailInputLayout);
        password = findViewById(R.id.signupPasswordEditText);
        passwordLayout = findViewById(R.id.signupPasswordInputLayout);
        toolbar = findViewById(R.id.signupRegisterToolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.registerProgressBar);
        viewTint = findViewById(R.id.registerViewTint)
    }

    public boolean createAccount(){
        return true;
    }

    public void setUnclickable(){
        confirm.setClickable(false);
        emailLayout.setClickable(false);
        passwordLayout.setClickable(false);
        email.setClickable(false);
        password.setClickable(false);
        toolbar.setClickable(false);
    }

    public void setClickable(){
        confirm.setClickable(true);
        emailLayout.setClickable(true);
        passwordLayout.setClickable(true);
        email.setClickable(true);
        password.setClickable(true);
        toolbar.setClickable(true);
    }
}