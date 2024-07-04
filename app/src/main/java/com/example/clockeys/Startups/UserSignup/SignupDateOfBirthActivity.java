package com.example.clockeys.Startups.UserSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SignupDateOfBirthActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton birthdayButton;
    private TextView birthdayTV;
    private Button confirmButton;
    private Employee employee;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_date_of_birth);
        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);


        bindViews();
        setViews();

        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
                constraintBuilder.setValidator(DateValidatorPointBackward.now());

                MaterialDatePicker<Long> birthdayDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Date of Birth")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setCalendarConstraints(constraintBuilder.build())
                        .build();
                birthdayDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Date selectedDate = new Date(selection);
                        employee.setDateOfBirth(selectedDate);
                        setViews();
                    }
                });
                birthdayDatePicker.show(getSupportFragmentManager(),"birthday");
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNext();
            }
        });

    }

    public void bindViews(){
        toolbar = findViewById(R.id.signupDOBToolbar);
        birthdayTV = findViewById(R.id.signupDOBTV);
        birthdayButton = findViewById(R.id.signupDOBButton);
        confirmButton = findViewById(R.id.signupDOBConfirmButton);
        setSupportActionBar(toolbar);
    }

    public void setViews(){
        if (employee.getDateOfBirth() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            // Format the date using the SimpleDateFormat
            String date = sdf.format(employee.getDateOfBirth());

            // Display the formatted date
            birthdayTV.setText(date);
        }
    }

    public void launchNext(){
        if (employee.getDateOfBirth() != null){
            Intent nextIntent = new Intent();
            nextIntent.putExtra("employee",employee);
        }
    }


}