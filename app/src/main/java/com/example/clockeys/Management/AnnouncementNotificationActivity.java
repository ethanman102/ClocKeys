package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.clockeys.Notifications.AnnouncementNotification;
import com.example.clockeys.Notifications.Notification;
import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AnnouncementNotificationActivity extends AppCompatActivity {

    private Button createButton;
    private ImageButton dismissDateButton;
    private TextView dismissDate;
    private Toolbar toolbar;
    private TextInputEditText title,announcement;
    private Slider urgencySlider;
    private Employee poster;
    private int companyId;
    private Date dismissDateObject;
    private Intent recievedIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_notification);

        recievedIntent = getIntent();
        poster = recievedIntent.getSerializableExtra("employee", Employee.class);
        companyId = recievedIntent.getIntExtra("companyId",0);

        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });

        dismissDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
                constraintBuilder.setValidator(DateValidatorPointForward.now());

                MaterialDatePicker<Long> dismissalDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Date of Dismissal")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setCalendarConstraints(constraintBuilder.build())
                        .build();
                dismissalDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        dismissDateObject = new Date(selection);
                        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
                        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                        dismissDate.setText(sdf.format(dismissDateObject));

                    }
                });
                dismissalDatePicker.show(getSupportFragmentManager(),"birthday");
            }
        });

    }


    public void bindViews(){
        createButton = findViewById(R.id.announcementCreateButton);
        dismissDateButton = findViewById(R.id.announcementDismissButton);
        dismissDate = findViewById(R.id.announcementDismissDate);
        title = findViewById(R.id.announcementTitleEditText);
        announcement = findViewById(R.id.announcementTextEditText);
        urgencySlider = findViewById(R.id.urgencySlider);

        toolbar = findViewById(R.id.announcementNotificationToolbar);
        setSupportActionBar(toolbar);
    }

    public void createNotification(){

        String titleText = title.getText().toString();
        if (titleText.trim().isEmpty()){
            return;
        }
        String announcementText = announcement.getText().toString();
        if (announcementText.trim().isEmpty()){
            return;
        }if (dismissDateObject == null){
            return;
        }

        // Else the case when our notification has no errors and can be successfully created.

        Notification notification = new AnnouncementNotification(
                898,
                companyId,
                titleText,
                new Date(),
                (int) urgencySlider.getValue(),
                poster.getEmployeeNumber(),
                poster.getName(),
                dismissDateObject,
                announcementText);



        Intent intent = new Intent(this,NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("notification",notification);
        startActivity(intent);
        finish();
    }

}