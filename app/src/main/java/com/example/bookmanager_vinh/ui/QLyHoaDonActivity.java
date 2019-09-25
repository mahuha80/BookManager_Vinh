package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.QuanLyHoaDonAdapter;

public class QLyHoaDonActivity extends AppCompatActivity {
    ListView lvHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        lvHoaDon=findViewById(R.id.lvHoaDon);
        lvHoaDon.setAdapter(new QuanLyHoaDonAdapter(this));
    }
}
