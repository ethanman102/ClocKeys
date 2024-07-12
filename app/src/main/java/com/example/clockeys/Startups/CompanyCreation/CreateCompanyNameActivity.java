package com.example.clockeys.Startups.CompanyCreation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateCompanyNameActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button confirmButton;
    private TextInputEditText companyName;
    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_company_name);

        company = new Company();


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
                String name = companyName.getText().toString().trim();
                if (!name.isEmpty()){
                    company.setCompanyName(name);
                    launchNext();
                }
            }
        });
    }


    public void bindViews(){
        toolbar = findViewById(R.id.createCompanyNameToolbar);
        confirmButton = findViewById(R.id.createCompanyNameButton);
        companyName = findViewById(R.id.createCompanyNameEditText);
        setSupportActionBar(toolbar);
    }

    public void setViews(){
        if (company.getCompanyName() != null){
            companyName.setText(company.getCompanyName());
        }
    }

    public void launchNext(){
        Intent nextIntent = new Intent(this, CreateCompanyAddressActivity.class);
        nextIntent.putExtra("company",company);
        startActivity(nextIntent);
    }

}