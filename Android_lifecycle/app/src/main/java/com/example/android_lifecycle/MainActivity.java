package com.example.android_lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.lifecycle_textView);
        Log.d("MainActivity","OnCreate Call");
        displayMessage("onCreate");
    }

    protected void onStart(){
        super.onStart();
        displayMessage("onStart");
        Log.d("MainActivity","OnStartCall");
    }

    protected void onResume(){
        super.onResume();
        displayMessage("onResume");
        Log.d("MainActivity","OnResumeCall");
    }

    protected void onPause(){
        super.onPause();
        displayMessage("onPause");
        Log.d("MainActivity","OnPauseCall");
    }

    protected void onStop(){
        super.onStop();
        displayMessage("onStop");
        Log.d("MainActivity","OnStopCall");
    }

    protected void onDestroy(){
        super.onDestroy();
        displayMessage("onDestroy");
        Log.d("MainActivity","OnDestroyCall");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayMessage("onRestoreInstanceState");
    }
    private void displayMessage(String message) {
        textView.setText(message);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setTextSize(24f);
    }
}