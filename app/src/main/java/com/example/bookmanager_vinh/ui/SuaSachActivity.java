package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;
import com.example.bookmanager_vinh.model.Sach;

public class SuaSachActivity extends AppCompatActivity {
    Intent intent;
    Spinner spinner;
    TheLoaiDAO theLoaiDAO;
    private Button btnSuaSach;
    private EditText edMaSach, edTenSach, edTacGia, edNXB, edGia, edSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sach);
        init();

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Sach sach = (Sach) bundle.getSerializable("Sach");
        if (sach != null) {
            edMaSach.setText(sach.getMasach());
            edTenSach.setText(sach.getTensach());
            edTacGia.setText(sach.getTacgia());
            edNXB.setText(sach.getTensach());
            edGia.setText(sach.getGiabia());
            edSoLuong.setText(sach.getSoluong());
        }
    }

    private void init() {
        spinner = findViewById(R.id.spTheLoaiSach);
        theLoaiDAO = new TheLoaiDAO(this);
        btnSuaSach = findViewById(R.id.btnSuaSach);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTacGia);
        edNXB = findViewById(R.id.edGia);
        edTacGia = findViewById(R.id.edTacGia);
        edGia = findViewById(R.id.edGia);
        edSoLuong = findViewById(R.id.edSoLuong);

    }
}
