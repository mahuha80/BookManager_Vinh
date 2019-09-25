package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        CountDownTimer countDownTimer=new CountDownTimer(1500,1500) {
            @Override
            public void onTick(long l) {
                Toast.makeText(SplashScreenActivity.this, "onTick", Toast.LENGTH_SHORT).show();

                }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));

            }
        };
        countDownTimer.start();
    }
}
