package com.example.debitcard;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    TextView t1,t2,t3,t4,t5;
    Button proceed;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        e1= findViewById(R.id.cardholder);
        e2 = findViewById(R.id.one);
        e3 = findViewById(R.id.two);
        e4 = findViewById(R.id.three);
        e5 = findViewById(R.id.four);
        e6 = findViewById(R.id.mm);
        e7 = findViewById(R.id.yy);
        e8 = findViewById(R.id.cvv);


        t1 = findViewById(R.id.textcardholder);
        t2 = findViewById(R.id.textcardnumber);
        t3 = findViewById(R.id.textexp);
        t4 = findViewById(R.id.textcvv);


        Button proceed = findViewById(R.id.proceedbtn);

        mediaPlayer  = MediaPlayer.create(this,R.raw.success);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime);
                LinearLayout linearlayout = findViewById(R.id.linerLayoutCard);
                linearlayout.startAnimation(animation);

                String code1,code2,code3,code4;
                code1 = e2.getText().toString();
                code2 = e3.getText().toString();
                code3 = e4.getText().toString();
                code4 = e5.getText().toString();


                t2.setText (code1 + "\t" + code2 + "\t" + code3 + "\t" + code4);


                String name, mm,yy, cvv;

                name = e1.getText().toString();
                t1.setText(name);

                mm = e6.getText().toString();
                yy = e7.getText().toString();
                t3.setText(mm+"/"+yy);

                cvv = e8.getText().toString();
                t4.setText(cvv);

                toggleAudio();
            }
        });
    }
    private void toggleAudio(){
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
            else {
                mediaPlayer.start();
            }
        }

    }

    protected  void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}