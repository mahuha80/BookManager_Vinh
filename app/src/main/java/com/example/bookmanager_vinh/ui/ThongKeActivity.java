package com.example.bookmanager_vinh.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.fragment.FragmentNode;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvHomNay, tvThangNay, tvNamNay;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentNode fragmentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        iconBack();
        viewPager = findViewById(R.id.vpThongKe);
        tabLayout = findViewById(R.id.tlThongKe);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentNode = new FragmentNode(fragmentManager);
        viewPager.setAdapter(fragmentNode);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

//        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
//        tvHomNay.setText("Hôm nay :" + hoaDonChiTietDAO.getDoanhThuTheoNgay());
//        tvThangNay.setText("Tháng này: " + hoaDonChiTietDAO.getDoanhThuTheoThang() + "");
//        tvNamNay.setText("Năm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNam() + "");
//        tvHomNay = findViewById(R.id.tvHomNay);
//        tvThangNay = findViewById(R.id.tvThangNay);
//        tvNamNay = findViewById(R.id.tvNamNay);