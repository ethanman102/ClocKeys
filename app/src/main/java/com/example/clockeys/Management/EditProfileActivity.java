package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;
import com.example.clockeys.Time.Timecard;
import com.example.clockeys.Users.Employee;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.io.FileInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileActivity extends AppCompatActivity {

    private Employee employee;
    private Toolbar toolbar;
    private RelativeLayout name,birthdate,address,bio;
    private CircleImageView profilePicture;
    private TextView leaveCompany,deleteProfile,nameTV,bioTV,addressTV,birthdayTV,editPhoto;

    private ActivityResultLauncher<Intent> profileInputActivityResultLauncher,cameraActivityResultLauncher;
    private Intent updatedIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        updatedIntent = new Intent();

        employee = getIntent().getSerializableExtra("employee",Employee.class);

        profileInputActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                employee = intent.getSerializableExtra("employee", Employee.class);
                setViews();
            }

        });

        cameraActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                String imageFile = intent.getStringExtra("imageFile");
                try{
                    FileInputStream fileInputStream = EditProfileActivity.this.openFileInput(imageFile);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    profilePicture.setImageBitmap(bitmap);
                }catch (Exception e){
                    Log.d("IMAGENOTIF", "saveBitmapToDisk: errorrrr...");
                    e.printStackTrace();
                }

            }

        });



        bindViews();
        setViews();

        editPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditName();
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditBio();
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditAddress();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,updatedIntent);
                finish();
            }
        });

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> birthdayDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Date of Birth")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.confirmButton){
            updatedIntent.putExtra("employee",(Serializable) employee);
            setResult(RESULT_OK,updatedIntent);
        }
        updatedIntent.putExtra("employee",(Serializable) employee);
        setResult(RESULT_OK,updatedIntent);

        finish();
        return Boolean.TRUE;
    }
    public void launchEditName(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);

        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getName());
        intent.putExtra("hint",getString(R.string.name));
        intent.putExtra("description",getString(R.string.name_description));
        intent.putExtra("length",30);

        profileInputActivityResultLauncher.launch(intent);
    }

    public void launchEditBio(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);
        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getBio());
        intent.putExtra("hint",getString(R.string.bio));
        intent.putExtra("description",getString(R.string.bio_description));
        intent.putExtra("length",150);

        profileInputActivityResultLauncher.launch(intent);
    }

    public void launchEditAddress(){
        Intent intent = new Intent(EditProfileActivity.this,ProfileInputActivity.class);
        intent.putExtra("employee",employee);
        intent.putExtra("text",employee.getAddress());
        intent.putExtra("hint",getString(R.string.address));
        intent.putExtra("description",getString(R.string.address_description));
        intent.putExtra("length",40);

        profileInputActivityResultLauncher.launch(intent);

    }
    private void bindViews(){

        // binding the layouts

        name = findViewById(R.id.nameLayout);
        bio = findViewById(R.id.bioLayout);
        birthdate = findViewById(R.id.birthdayLayout);
        address = findViewById(R.id.addressLayout);

        toolbar = findViewById(R.id.editProfileToolbar);
        setSupportActionBar(toolbar);

        bioTV = findViewById(R.id.employeeBio);
        nameTV = findViewById(R.id.employeeNameChangeable);
        addressTV = findViewById(R.id.employeeAddress);
        birthdayTV = findViewById(R.id.employeeBirthdayDate);

        profilePicture = findViewById(R.id.editProfileProfilePicture);
        editPhoto = findViewById(R.id.editPictureTV);



    }

    private void setViews(){
        nameTV.setText(employee.getName());
        bioTV.setText(employee.getBio());
        addressTV.setText(employee.getAddress());

        // Create a SimpleDateFormat with the desired format and time zone
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Format the date using the SimpleDateFormat
        String date = sdf.format(employee.getDateOfBirth());

        // Display the formatted date
        birthdayTV.setText(date);
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        RelativeLayout cameraActivity = dialog.findViewById(R.id.openCameraActivityLayout);
        RelativeLayout removePhoto = dialog.findViewById(R.id.removePhotoLayout);

        removePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePicture.setImageResource(R.drawable.mcdonalds);
                dialog.dismiss();
            }
        });

        cameraActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(EditProfileActivity.this,CameraActivity.class);
                cameraActivityResultLauncher.launch(cameraIntent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }
}