package com.example.musical_instruments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        GifImageView gifImageView1 = findViewById(R.id.m1);
        GifImageView gifImageView2 = findViewById(R.id.m2);
        GifImageView gifImageView3 = findViewById(R.id.m3);
        GifImageView gifImageView4 = findViewById(R.id.m4);
        GifImageView gifImageView5 = findViewById(R.id.m5);
        GifImageView gifImageView6 = findViewById(R.id.m6);

        gifImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.drum);
            }
        });

        gifImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.electricguitar);
            }
        });

        gifImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.harmonica);
            }
        });

        gifImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.harp);
            }
        });

        gifImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.piano);
            }
        });

        gifImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(R.raw.xylophone);
            }
        });
    }
    private void playMusic(int resId) {
        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
        mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();
    }

    protected  void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}