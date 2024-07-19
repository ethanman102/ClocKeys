package com.example.clockeys.Management;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.clockeys.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class CameraActivity extends AppCompatActivity implements ConfirmPhotoFragment.onConfirmPhotoFragmentInteractionListener{

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ActivityResultLauncher<Intent> galleryResultLauncher;
    private ImageButton galleryButton,pictureButton,backButton;
    private PreviewView previewView;
    private ImageCapture imageCapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        bindViews();

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGallery();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCapture.takePicture(getExecutor(), new ImageCapture.OnImageCapturedCallback() {
                    @Override
                    public void onCaptureSuccess(@NonNull ImageProxy image) {
                        setViewsInvisible();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
                        ConfirmPhotoFragment confirmPhotoFragment = ConfirmPhotoFragment.newInstance(bitmapImage,CameraActivity.this);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.cameraFragmentFrameLayout,confirmPhotoFragment);
                        fragmentTransaction.addToBackStack("confirmFragment");
                        fragmentTransaction.commit();
                        image.close();
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        super.onError(exception);
                    }
                });
            }

        });

        cameraProviderFuture= ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(()->{
            try {
               ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
               startCameraX(cameraProvider);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },getExecutor());

    }

    private Executor getExecutor() {
        return ContextCompat.getMainExecutor(this);
    }

    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();

        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build();

        cameraProvider.bindToLifecycle(this,cameraSelector,preview,imageCapture);
    }

    public void bindViews(){
        galleryButton = findViewById(R.id.galleryButton);
        pictureButton = findViewById(R.id.pictureButton);
        backButton = findViewById(R.id.cameraBackButton);
        previewView = findViewById(R.id.cameraActivityPreviewView);

    }

    @Override
    public void onConfirmPressed(Bitmap bitmap) {
        this.getSupportFragmentManager().popBackStack(); // remove the current fragment
        setViewsVisible();
        Intent updatedIntent = new Intent();
        updatedIntent.putExtra("imageFile",saveBitmapToDisk(bitmap));
        setResult(RESULT_OK,updatedIntent);
        finish();
    }

    @Override
    public void onRejectPressed() {
        this.getSupportFragmentManager().popBackStack();
        setViewsVisible();// just rejected the bitmap
    }

    public void setViewsInvisible(){
        pictureButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);
        galleryButton.setVisibility(View.GONE);
    }

    public void setViewsVisible(){
        pictureButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
        galleryButton.setVisibility(View.VISIBLE);
    }

    public String saveBitmapToDisk(Bitmap bitmap){
        // source code via stack overflow and used for my project.
        // https://stackoverflow.com/questions/11010386/passing-android-bitmap-data-within-activity-using-intent-in-android
        // Special thanks to user starball for the Uri conversion!
        String filename = "bitmap.png";
        try{
            Context context = getApplicationContext();
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);

            fileOutputStream.close();
            bitmap.recycle();

        } catch (Exception exception){
            Log.d("CAMACTIVITY", "saveBitmapToDisk: errorrrr...");
            exception.printStackTrace();
        }
        return filename;
    }

    public void launchGallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        galleryResultLauncher.launch(Intent.createChooser(galleryIntent,"picture"));
    }
}