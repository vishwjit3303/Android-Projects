package com.example.admossionform;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name,email,birthdate,phoneno,address,nationality,blood;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        birthdate = findViewById(R.id.birthdate);
        phoneno = findViewById(R.id.phoneno);
        address = findViewById(R.id.address);
        nationality = findViewById(R.id.nationality);
        blood = findViewById(R.id.blood);



    }
}