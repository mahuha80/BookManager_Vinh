package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;

import java.util.Calendar;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvHomNay, tvThangNay, tvNamNay;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvHomNay = findViewById(R.id.tvHomNay);
        tvThangNay = findViewById(R.id.tvThangNay);
        tvNamNay = findViewById(R.id.tvNamNay);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvHomNay.setText("Hôm nay :"+hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThangNay.setText("Tháng này: "+hoaDonChiTietDAO.getDoanhThuTheoThang()+"");
        tvNamNay.setText("Năm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNam()+"");



    }
}
