package com.example.crudwithfirebase;

import android.icu.util.Output;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText mobile;
    EditText dob;
    EditText city;

    Button insert;
    Button delete;
    Button update;
    Button view;

    TextView nametxt;
    TextView mobtxt;
    TextView dobtxt;
    TextView citytxt;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_text_one);
        mobile = findViewById(R.id.edit_text_two);
        dob = findViewById(R.id.edit_text_three);
        city = findViewById(R.id.edit_text_four);

        insert = findViewById(R.id.insertbtn1);
        delete = findViewById(R.id.deletebtn2);
        update = findViewById(R.id.updatebtn3);
        view = findViewById(R.id.readbtn4);

        nametxt = findViewById(R.id.nametxt);
        mobtxt = findViewById(R.id.mobtxt);
        dobtxt = findViewById(R.id.dobtxt);
        citytxt = findViewById(R.id.citytxt);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Data").child("Users");

        insert.setOnClickListener(
                v -> {
                    String name1 = name.getText().toString();
                    String mobile1 = mobile.getText().toString();
                    String dob1 = dob.getText().toString();
                    String city1 = city.getText().toString();

                    HashMap hashMap = new HashMap();
                    hashMap.put("name", name1);
                    hashMap.put("mobile", mobile1);
                    hashMap.put("dob", dob1);
                    hashMap.put("city", city1);

                    databaseReference.child("user1").setValue(hashMap).addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    });

                }
        );

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("user1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                            Object name1 = map.get("name");
                            Object mobile1 = map.get("mobile");
                            Object dob1 = map.get("dob");
                            Object city1 = map.get("city");

                            nametxt.setText(name1.toString());
                            mobtxt.setText(mobile1.toString());
                            dobtxt.setText(dob1.toString());
                            citytxt.setText(city1.toString());

                        } else {
                            Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        update.setOnClickListener(
                v -> {
                    String name1 = name.getText().toString();
                    String mobile1 = mobile.getText().toString();
                    String dob1 = dob.getText().toString();
                    String city1 = city.getText().toString();

                    HashMap hashMap = new HashMap();
                    hashMap.put("name", name1);
                    hashMap.put("mobile", mobile1);
                    hashMap.put("dob", dob1);
                    hashMap.put("city", city1);

                    databaseReference.child("user1").updateChildren(hashMap).addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Data Not updated", Toast.LENGTH_SHORT).show();
                    });

                });

        delete.setOnClickListener(v -> {
            databaseReference.child("user1").removeValue().addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();

            });
        });
    }
}