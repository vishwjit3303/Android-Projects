package com.example.masterproject;

import  android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SchoolRegistration extends Fragment {

//    private static final int PICK_IMAGE_REQUEST = 1;
//    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CAMERA_PERMISSION_CODE = 100;


    private ImageView imageView;
    private Button button;
    private ImageView successImageview;
    private Uri imageUri;
    private File photoFile;
    private TextView datetextView;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private RadioButton otherRadioButton;


    // Camera Launcher
    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK) {
                    loadImage();
                } else {
                    Toast.makeText(getContext(), "Camera operation failed", Toast.LENGTH_SHORT).show();
                }
            }
    );
    // Gallery Launcher
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    loadImage();
                } else {
                    Toast.makeText(getContext(), "Gallery operation failed", Toast.LENGTH_SHORT).show();
                }
            }
    );

    // Permission Request Launcher
    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    openCamera();
                } else {
                    Toast.makeText(getContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            }
    );


    public SchoolRegistration() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_registration, container, false);

        imageView = view.findViewById(R.id.myImageView);
        button = view.findViewById(R.id.uploadbtn);
        successImageview = view.findViewById(R.id.successimg);

        button.setOnClickListener(v -> showImagePickerOptions());

        datetextView = view.findViewById(R.id.dateTextView);

        maleRadioButton = view.findViewById(R.id.maleRadioButton);
        femaleRadioButton = view.findViewById(R.id.femaleRadioButton);
        otherRadioButton = view.findViewById(R.id.otherRadioButton);
        datetextView.setOnClickListener(v->showDatePicker());


        maleRadioButton.setOnClickListener( v -> {
            Toast.makeText(getContext(), "Selected Male", Toast.LENGTH_SHORT).show();
        });

        femaleRadioButton.setOnClickListener( v -> {
            Toast.makeText(getContext(), "Selected female", Toast.LENGTH_SHORT).show();
        });

        otherRadioButton.setOnClickListener( v -> {
            Toast.makeText(getContext(), "Selected other", Toast.LENGTH_SHORT).show();
        });


        return view;
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    datetextView.setText(selectedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void showImagePickerOptions() {
        String[] options = {"Choose From Gallery", "Take a Photo"};
        new android.app.AlertDialog.Builder(getContext())
                .setTitle("Select options")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        openGallery();
                    } else if (which == 1) {
                        // Check for Camera and Write External Storage permissions
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
                        } else {
                            openCamera();  // Permissions are already granted, open the camera
                        }
                    }
                }).show();
    }


    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }
    @SuppressLint("LongLogTag")
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(getContext(), "com.example.masterproject.fileprovider", photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    cameraLauncher.launch(cameraIntent);
                }
            } catch (IOException ex) {
                Log.e("SchoolRegistration", "Error creating image file", ex);
                Toast.makeText(getContext(), "Error occurred while creating the file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }



    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(null);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
    private void loadImage() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            imageView.setImageBitmap(bitmap);
            successImageview.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }
}


