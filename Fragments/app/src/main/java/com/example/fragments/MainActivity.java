package com.example.fragments;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Get FragmentManager
        FragmentManager fragmentmanager = getSupportFragmentManager();


        // Load the first fragment bydefault

        fragmentmanager.beginTransaction()
                .replace(R.id.fragmentContainer, new Fragment_One())
                .commit();

        // Button for fragment one

        Button btnfragmentOne = findViewById(R.id.btnfragment_one);
        btnfragmentOne.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_One())
                    .commit();
        });

        Button btnfragmentTwo = findViewById(R.id.btnfragment_two);
        btnfragmentTwo.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Two())
                    .commit();
        });

        Button btnfragmentThree = findViewById(R.id.btnfragment_three);
        btnfragmentThree.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Three())
                    .commit();
        });

        Button btnfragmentFour = findViewById(R.id.btnfragment_four);
        btnfragmentFour.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Four())
                    .commit();
        });

        Button btnfragmentFive = findViewById(R.id.btnfragment_twoone);
        btnfragmentFive.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Five())
                    .commit();
        });

        Button btnfragmentSix = findViewById(R.id.btnfragment_twotwo);
        btnfragmentSix.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Six())
                    .commit();
        });

        Button btnfragmentSeven = findViewById(R.id.btnfragment_twothree);
        btnfragmentSeven.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Seven())
                    .commit();
        });

        Button btnfragmentEight = findViewById(R.id.btnfragment_twofour);
        btnfragmentEight.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Eight())
                    .commit();
        });

        Button btnfragmentNine = findViewById(R.id.btnfragment_threeone);
        btnfragmentNine.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Nine())
                    .commit();
        });

        Button btnfragmentTen = findViewById(R.id.btnfragment_threetwo);
        btnfragmentTen.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Ten())
                    .commit();
        });

        Button btnfragmentEleven = findViewById(R.id.btnfragment_threethree);
        btnfragmentEleven.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Eleven())
                    .commit();
        });

        Button btnfragmentTwelve = findViewById(R.id.btnfragment_threefour);
        btnfragmentTwelve.setOnClickListener(v ->{
            fragmentmanager.beginTransaction()
                    .replace(R.id.fragmentContainer, new Fragment_Twelve())
                    .commit();
        });


    }
}