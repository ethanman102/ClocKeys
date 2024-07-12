package com.example.clockeys.Startups.CompanyCreation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clockeys.Models.Company;
import com.example.clockeys.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateCompanyAddressActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button confirmButton;
    private TextInputEditText address;
    private Intent intent;
    private Company company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_company_address);

        intent = getIntent();
        company = intent.getSerializableExtra("company", Company.class);

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
                String input = address.getText().toString().trim();
                if (!input.isEmpty()){
                    company.setAddress(input);
                    launchNext();
                }
            }
        });
    }

    public void bindViews(){
        toolbar = findViewById(R.id.createCompanyAddressToolbar);
        setSupportActionBar(toolbar);
        confirmButton = findViewById(R.id.createCompanyAddressButton);
        address = findViewById(R.id.createCompanyAddressEditText);
    }
    public void setViews(){
        if (company.getAddress() != null){
            address.setText(company.getAddress());
        }
    }
    public void launchNext(){
        Intent nextIntent = new Intent(this,CreateCompanyLogoActivity.class);
        nextIntent.putExtra("company",company);
        startActivity(nextIntent);
    }


}