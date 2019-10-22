package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

public class MainActivity extends AppCompatActivity {
    ImageView imgNguoiDung, imgTheLoai, imgHoaDon, imgSach,imgSachBanChay,imgThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgNguoiDung = findViewById(R.id.imgNguoiDung);
        imgTheLoai = findViewById(R.id.imgTheLoai);
        imgHoaDon = findViewById(R.id.imgHoaDon);
        imgSach = findViewById(R.id.imgSach);
        imgSachBanChay=findViewById(R.id.imgSachBanChay);
        imgThongKe=findViewById(R.id.imgThongKe);
        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NguoiDungActivity.class));
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
                startActivity(new Intent(MainActivity.this, ThemHoaDonActivity.class));

            }
        });
        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QLySachActivity.class));

            }
        });
        imgSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SachBanChayActivity.class));

            }
        });
        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThongKeActivity.class));

            }
        });

    }
}
