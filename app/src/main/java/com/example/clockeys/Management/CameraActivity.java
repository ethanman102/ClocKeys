import androidx.annotation.NonNull;

private PreviewView previewView;
private ImageView imageView;
private ImageCapture imageCapture;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.previewView);
        imageView = findViewById(R.id.imageView);
        Button captureButton = findViewById(R.id.captureButton);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
        startCamera();
        }

        captureButton.setOnClickListener(v -> takePhoto());
        }

private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
        try {
        ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
        bindPreview(cameraProvider);
        } catch (ExecutionException | InterruptedException e) {
        Log.e("CameraXApp", "Error starting camera", e);
        }
        }, ContextCompat.getMainExecutor(this));
        }

private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder().build();

        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

        try {
        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
        } catch (Exception e) {
        Log.e("CameraXApp", "Error binding camera preview", e);
        }
        }

private void takePhoto() {
        File photoFile = new File(getExternalFilesDir(null), System.currentTimeMillis() + ".jpg");

        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
@Override
public void onError(@NonNull ImageCaptureException exception) {
        Log.e("CameraXApp", "Photo capture failed: " + exception.getMessage(), exception);
        }

@Override
public void onImageSaved(@NonNull ImageCapture.OutputFileResults output) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        runOnUiThread(() -> {
        previewView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(bitmap);
        });
        }
        });
        }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        startCamera();
        } else {
        Log.e("CameraXApp", "Camera permission denied");
        }
        }
        }
