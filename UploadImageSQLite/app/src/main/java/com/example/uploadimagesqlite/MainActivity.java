package com.example.uploadimagesqlite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText imageDetailET;
    ImageView objectImageView;
    DatabaseHandler objectDatabaseHandler;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            imageDetailET = findViewById(R.id.edittxt);
            objectImageView = findViewById(R.id.uploadimg);

            objectDatabaseHandler = new DatabaseHandler(this);
        } catch (Exception e ){
            Toast.makeText(this, "Initialization error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void chooseImage(View objectView){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_REQUEST);
        } catch (Exception e){
            Toast.makeText(this, "Error choosing image"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                objectImageView.setImageBitmap(imageToStore);
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(this, "Error Loading image"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public  void storeImage(View objectView){
        try {
            if(!imageDetailET.getText().toString().isEmpty() && imageToStore != null){
                objectDatabaseHandler.storeImage(new ModalClass(imageDetailET.getText().toString(), imageToStore));
            } else {
                Toast.makeText(this, "please enter the image name", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e ){
            Toast.makeText(this, "Error storing image"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}