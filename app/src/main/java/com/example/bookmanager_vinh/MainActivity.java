package com.example.bookmanager_vinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imgNguoiDung, imgTheLoai,imgHoaDon,imgSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgNguoiDung = findViewById(R.id.imgNguoiDung);
        imgTheLoai=findViewById(R.id.imgTheLoai);
        imgHoaDon=findViewById(R.id.imgHoaDon);
        imgSach=findViewById(R.id.imgSach);
        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
        imgTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QLyLoaiSachActivity.class));

            }
        });
        imgHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QLyHoaDonActivity.class));

            }
        });
        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QLySachActivity.class));

            }
        });

    }
}
