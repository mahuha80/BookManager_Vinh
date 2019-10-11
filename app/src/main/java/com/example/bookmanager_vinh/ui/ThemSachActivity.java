package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.ThemSachAdapter;
import com.example.bookmanager_vinh.dao.SachDAO;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;
import com.example.bookmanager_vinh.model.LoaiSach;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

//import com.example.bookmanager_vinh.adapter.ThemSachAdapter;

public class ThemSachActivity extends AppCompatActivity {
    Spinner spinner;
    TheLoaiDAO theLoaiDAO;
    SachDAO sachDAO;
    List<LoaiSach> listTheLoaiSach;
    Intent intent;
    private Button btnThemSach;
    private EditText edMaSach, edTenSach, edTacGia, edNXB, edGia, edSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        init();
        listTheLoaiSach = theLoaiDAO.getAllLoaiSach();
        ThemSachAdapter themSachAdapter = new ThemSachAdapter(this, listTheLoaiSach);
        spinner.setAdapter(themSachAdapter);
        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isThemSach()) {
                    Toast.makeText(ThemSachActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ThemSachActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void finishActivity(View v) {
        finish();
    }

    private boolean isThemSach() {
        LoaiSach loaiSach = (LoaiSach) spinner.getSelectedItem();
        String maSach = edMaSach.getText().toString();
        String tenSach = edTenSach.getText().toString();
        String tacGia = edTacGia.getText().toString();
        String nxv = edNXB.getText().toString();
        String gia = edGia.getText().toString();
        String soLuong = edSoLuong.getText().toString();
        Log.e("BUGG", loaiSach.getMaTheLoai() + "");
        long result = sachDAO.insertSach(new Sach(maSach, loaiSach.getMaTheLoai(), tenSach, tacGia, nxv, gia, soLuong));

        return result > 0;
    }

    private void init() {
        spinner = findViewById(R.id.spTheLoaiSach);
        theLoaiDAO = new TheLoaiDAO(this);
        listTheLoaiSach = new ArrayList<>();
        btnThemSach = findViewById(R.id.btnThemSach);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTacGia);
        edNXB = findViewById(R.id.edGia);
        edTacGia = findViewById(R.id.edTacGia);
        edGia = findViewById(R.id.edGia);
        edSoLuong = findViewById(R.id.edSoLuong);
        sachDAO = new SachDAO(this);


    }
}
