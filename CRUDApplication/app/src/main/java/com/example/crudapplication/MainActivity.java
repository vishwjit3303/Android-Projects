package com.example.crudapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob, city;
    Button insert, update, delete, view;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_text_one);
        contact = findViewById(R.id.edit_text_two);
        dob = findViewById(R.id.edit_text_three);
        city = findViewById(R.id.edit_text_four);

        insert = findViewById(R.id.btn1);
        delete = findViewById(R.id.btn2);
        update = findViewById(R.id.btn3);
        view = findViewById(R.id.btn4);
        db= new DataBase(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String dobtxt = dob.getText().toString();
                String citytxt = city.getText().toString();

                Boolean checkInsertData = db.insertUserData(nametxt,contacttxt,dobtxt,citytxt);
                if(checkInsertData)
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String dobtxt = dob.getText().toString();
                String citytxt = city.getText().toString();

                Boolean checkUpdateData = db.updateUserData(nametxt,contacttxt,dobtxt,citytxt);
                if(checkUpdateData)
                    Toast.makeText(MainActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();

                Boolean checkDeleteData = db.deleteUserData(nametxt);
                if(checkDeleteData)
                    Toast.makeText(MainActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this,"New Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+ "\n");
                    buffer.append("Contact: "+res.getString(1)+ "\n");
                    buffer.append("DOB: "+res.getString(2)+ "\n");
                    buffer.append("City: "+res.getString(3)+ "\n \n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}