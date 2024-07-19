package com.example.clockeys.Startups.CompanyCreation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.clockeys.Management.EditProfileActivity;
import com.example.clockeys.Models.Company;
import com.example.clockeys.R;

import java.io.FileInputStream;

public class CreateCompanyLogoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button confirm,remove;
    private ImageButton cameraButton;
    private ImageView logo;
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    private Company company;
    private Intent intent;

    private String logoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_company_logo);
        bindViews();

        intent = getIntent();
        company = intent.getSerializableExtra("company", Company.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cameraActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                String imageFile = intent.getStringExtra("imageFile");
                try{
                    FileInputStream fileInputStream = CreateCompanyLogoActivity.this.openFileInput(imageFile);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    logo.setImageBitmap(bitmap);
                    remove.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    Log.d("IMAGENOTIF", "saveBitmapToDisk: errorrrr...");
                    e.printStackTrace();
                }

            }

        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logo.setImageBitmap(null);
                remove.setVisibility(View.GONE);
            }
        });

    }


    public void bindViews(){
        toolbar = findViewById(R.id.createCompanyLogoToolbar);
        setSupportActionBar(toolbar);

        cameraButton = findViewById(R.id.logoPhotoButton);
        logo = findViewById(R.id.createCompanyLogoImage);
        remove = findViewById(R.id.removePhotoButton);
        confirm = findViewById(R.id.createCompanyLogoButton);
    }

}