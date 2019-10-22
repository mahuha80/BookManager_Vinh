package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        Toast.makeText(this, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"", Toast.LENGTH_SHORT).show();
        double doanhthungay= hoaDonChiTietDAO.getDoanhThuTheoNgay("Doanh thu theo ngày:" + hoaDonChiTietDAO.getDoanhThuTheoNgay("" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        Toast.makeText(this, doanhthungay+"", Toast.LENGTH_SHORT).show();

    }
}
