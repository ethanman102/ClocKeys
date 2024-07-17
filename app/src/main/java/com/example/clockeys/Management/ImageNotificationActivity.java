package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.clockeys.Adapters.ImageAdapter;
import com.example.clockeys.R;
import com.example.clockeys.Users.Employee;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ImageNotificationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageButton dismissDateButton, takePhotoButton;
    private Button createButton;
    private TextView dismissDateTextView;
    private RecyclerView imageRecyclerView;
    private TextInputEditText titleEditText, textEditText;
    private ArrayList<Bitmap> images;
    private ImageAdapter imageAdapter;
    private ActivityResultLauncher<Intent> imageNotificationActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_notification);

        bindViews();

        images = new ArrayList<>();
        imageAdapter = new ImageAdapter(this,images);
        imageRecyclerView.setAdapter(imageAdapter);

        imageRecyclerView.setLayoutManager(new CarouselLayoutManager());

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCameraActivity();
            }
        });

        imageNotificationActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{

            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Intent intent = result.getData();
                String imageFile = intent.getStringExtra("imageFile");
                try{
                    FileInputStream fileInputStream = ImageNotificationActivity.this.openFileInput(imageFile);
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    images.add(bitmap);
                    imageAdapter.notifyDataSetChanged();
                    Log.d("IMAGENOTIF", "# of images is " + Integer.toString(images.size()));
                }catch (Exception e){
                    Log.d("IMAGENOTIF", "saveBitmapToDisk: errorrrr...");
                    e.printStackTrace();
                }

            }

        });

    }

    public void bindViews(){
        toolbar = findViewById(R.id.imageNotificationToolbar);
        setSupportActionBar(toolbar);

        dismissDateButton = findViewById(R.id.imageDismissButton);
        takePhotoButton = findViewById(R.id.imageNotificationTakePhotoButton);

        createButton = findViewById(R.id.imageCreateButton);

        dismissDateTextView = findViewById(R.id.imageDismissDate);

        imageRecyclerView = findViewById(R.id.imageNotificationRecyclerView);

        titleEditText = findViewById(R.id.imageTitleEditText);
        textEditText = findViewById(R.id.imageTextEditText);
    }

    public void launchCameraActivity(){
        Intent intent = new Intent(ImageNotificationActivity.this,CameraActivity.class);
        imageNotificationActivityResultLauncher.launch(intent);
    }


}