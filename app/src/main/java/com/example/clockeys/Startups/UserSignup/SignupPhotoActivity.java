package com.example.clockeys.Startups.UserSignup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clockeys.Management.CameraActivity;
import com.example.clockeys.R;
import com.example.clockeys.Startups.CompanyCreation.CreateCompanyLogoActivity;
import com.example.clockeys.Users.Employee;

import java.io.FileInputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupPhotoActivity extends AppCompatActivity {

    private TextView photoButton;
    private Button confirm,remove;
    private CircleImageView photo;
    private Toolbar toolbar;
    private Intent intent;
    private Employee employee;
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    private String imageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_photo);

        bindViews();
        intent = getIntent();
        employee = intent.getSerializableExtra("employee", Employee.class);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCameraActivity();
            }
        });

        cameraActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                imageFile = intent.getStringExtra("imageFile");
                try{
                    FileInputStream fileInputStream = SignupPhotoActivity.this.openFileInput(imageFile);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    photo.setImageBitmap(bitmap);
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
                photo.setImageBitmap(null);
                remove.setVisibility(View.GONE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNext();
            }
        });

    }

    public void bindViews(){
        toolbar = findViewById(R.id.userSignupPhotoToolbar);
        setSupportActionBar(toolbar);

        remove = findViewById(R.id.userSignupRemovePhoto);
        photoButton = findViewById(R.id.userPhotoButton);
        confirm = findViewById(R.id.userPhotoConfirm);
        photo = findViewById(R.id.userSignupPhoto);
    }

    public void launchCameraActivity(){
        Intent cameraIntent = new Intent(SignupPhotoActivity.this, CameraActivity.class);
        cameraActivityResultLauncher.launch(cameraIntent);
    }

    public void launchNext(){
        Intent nextIntent = new Intent(SignupPhotoActivity.this,SignupRegisterActivity.class);
        nextIntent.putExtra("imagePath",imageFile);
        nextIntent.putExtra("employee",employee);
        startActivity(intent);
    }
}