package com.example.clockeys.Management;

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
import com.google.android.material.textfield.TextInputEditText;

public class AnnouncementNotificationActivity extends AppCompatActivity {

    private Button createButton;
    private ImageButton dismissDateButton;
    private TextView dismissDate;
    private Toolbar toolbar;
    private TextInputEditText title,announcement;
    private Employee poster;
    private Intent recievedIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_notification);

        recievedIntent = getIntent();
        poster = recievedIntent.getSerializableExtra("employee", Employee.class);

        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void bindViews(){
        createButton = findViewById(R.id.announcementCreateButton);
        dismissDateButton = findViewById(R.id.announcementDismissButton);
        dismissDate = findViewById(R.id.announcementDismissDate);
        title = findViewById(R.id.announcementTitleEditText);
        announcement = findViewById(R.id.announcementTextEditText);

        toolbar = findViewById(R.id.announcementNotificationToolbar);
        setSupportActionBar(toolbar);
    }

    public void createNotification(){

    }

}