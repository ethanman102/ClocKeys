package com.example.clockeys.Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;

public class ChooseNotificationActivity extends AppCompatActivity {

    private Button announcementButton, imageButton;
    private Toolbar toolbar;
    private Intent intent;
    private Company company;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_notification);

        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);
        company = intent.getSerializableExtra("company", Company.class);


        bindViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void bindViews(){
        announcementButton = findViewById(R.id.createAnnouncement);
        imageButton = findViewById(R.id.createImage);

        toolbar = findViewById(R.id.notificationToolbar);
        setSupportActionBar(toolbar);
    }

    public void startCreateAnnouncement(){
        Intent newIntent = new Intent(this,AnnouncementNotificationActivity.class);
        newIntent.putExtra("employee",employee);
        newIntent.putExtra("companyId",company.getCompanyID());
        startActivity(newIntent);
    }
}