package com.example.uploadimagesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {


    Context context;
    byte[] imageInByte;
    ByteArrayOutputStream byteArrayOutputStream;
    ArrayList<ModalClass> objectModalClassArrayList = new ArrayList<>();

    public DatabaseHandler(@Nullable Context context) {
        super(context, "image.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE ImageTable(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image BLOB)");
            Toast.makeText(context, "Table Created successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "Error creating table"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public  void storeImage(ModalClass modalClass){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStore = modalClass.getImage();

            // convert img to byte array
            byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStore.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            imageInByte = byteArrayOutputStream.toByteArray();

            // prepare content values
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", modalClass.getImage_name());
            contentValues.put("image", imageInByte);

            // Insert image data into database
            long checkIfQuery = db.insert("ImageTable", null, contentValues);

            if(checkIfQuery != 0){
                Toast.makeText(context, "Image Stored successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Error stroring image", Toast.LENGTH_SHORT).show();
            }
            db.close();

        } catch (Exception e){
            Toast.makeText(context, "Error storing image"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
