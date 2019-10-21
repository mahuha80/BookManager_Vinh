package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

public class SplashScreenActivity extends AppCompatActivity {
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        nguoiDungDAO = new NguoiDungDAO(this);
        boolean result = nguoiDungDAO.insertNguoiDung(new NguoiDung("admin", "admin", "0987395971", "Vinh NT", 1));
        if (result) {
            Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
        }
        CountDownTimer countDownTimer = new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {
                Toast.makeText(SplashScreenActivity.this, "onTick", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));

            }
        };
        countDownTimer.start();
    }
}
