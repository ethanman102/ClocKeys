package com.example.clockeys.Startups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.clockeys.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Signup extends AppCompatActivity {

    private ImageButton backButton;
    private Button birthdayButton;
    private TextView birthdayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Back Button
        backButton = findViewById(R.id.signupBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonPressed();
            }
        });

        // Birthday UI

        birthdayButton = findViewById(R.id.signupBirthdayButton);
        birthdayTextView = findViewById(R.id.signupBirthdayTextView);

        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> birthdayDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Date of Birth")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
                birthdayDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        // Create a SimpleDateFormat with the desired format and time zone
                        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
                        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                        // Convert the selection to a Date object
                        Date selectedDate = new Date(selection);

                        // Format the date using the SimpleDateFormat
                        String date = sdf.format(selectedDate);

                        // Display the formatted date
                        birthdayTextView.setText(date);
                    }
                });
                birthdayDatePicker.show(getSupportFragmentManager(),"birthday");
            }
        });

    }

    private void onBackButtonPressed(){
        finish();
    }
}
