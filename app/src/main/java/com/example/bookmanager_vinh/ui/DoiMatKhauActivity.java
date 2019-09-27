package com.example.bookmanager_vinh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edTenDangNhap, edMatKhau, edNhacLaiMatKhau;
    Button btnDoiMatKhau;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> nguoiDungList;
    List<String> tenNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        init();
        nguoiDungDAO = new NguoiDungDAO(DoiMatKhauActivity.this);
        nguoiDungList=new ArrayList<>();
        tenNguoiDung=new ArrayList<>();
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDangNhap = edTenDangNhap.getText().toString();
                String matKhau = edMatKhau.getText().toString();
                String nhacLaiMatKhau = edNhacLaiMatKhau.getText().toString();


            }
        });
    }

    private void init() {
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhacLaiMatKhau = findViewById(R.id.edNhacLaiMatKhau);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
    }

    public void finishActivitiy(View view) {
        finish();
    }
}
