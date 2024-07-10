package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.clockeys.R;

public class AnnouncementNotificationActivity extends AppCompatActivity {

    private Button createButton, dismissDateButton;
    private TextView posterName,posterId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_notification);
    }
}