package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvXemHoaDonChiTietAdapter;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.model.ThongKe;

import java.util.ArrayList;
import java.util.List;

public class XemHoaDonChiTiet extends AppCompatActivity {
    ListView lv;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    Intent intent;
    List<ThongKe> listThongKe;
    LvXemHoaDonChiTietAdapter lvXemHoaDonChiTietAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hoa_don_chi_tiet);
        lv = findViewById(R.id.lvXemHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        intent = getIntent();
        String mahoadon = intent.getStringExtra("mahoadon");
        listThongKe = new ArrayList<>();
        listThongKe = hoaDonChiTietDAO.getChiTietHoaDonTheoMaHoaDon(mahoadon);
        lvXemHoaDonChiTietAdapter = new LvXemHoaDonChiTietAdapter(this, listThongKe);
        lv.setAdapter(lvXemHoaDonChiTietAdapter);


    }
}
