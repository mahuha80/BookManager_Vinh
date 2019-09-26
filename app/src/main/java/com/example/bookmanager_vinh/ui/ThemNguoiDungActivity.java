package com.example.bookmanager_vinh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

public class ThemNguoiDungActivity extends AppCompatActivity {
    //btnThemNguoiDung,btnHienDanhSachNguoiDung;
    Button btnHuy;
    EditText edTenDangNhap, edMatKhau, edNhapLaiMatKhau, edSoDienThoai, edHoVaTen;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        init();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void addnewUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(ThemNguoiDungActivity.this);
        String username = edTenDangNhap.getText().toString();
        String pass = edMatKhau.getText().toString();
        String phone = edSoDienThoai.getText().toString();
        String fullname = edHoVaTen.getText().toString();
        NguoiDung nguoiDung = new NguoiDung(username, pass, phone, fullname);
        boolean isInsertTrue = nguoiDungDAO.insertNguoiDung(nguoiDung);
        if (isInsertTrue) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void danhSachNguoiDung(View view) {
        finish();
    }

    private void init() {
        btnHuy = findViewById(R.id.btnHuy);
//        btnThemNguoiDung=findViewById(R.id.btnThem);
//        btnHienDanhSachNguoiDung=findViewById(R.id.btnDanhSachNguoiDung);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhapLaiMatKhau = findViewById(R.id.edNhacLaiMatKhau);
        edSoDienThoai = findViewById(R.id.edPhone);
        edHoVaTen = findViewById(R.id.edHoVaTen);

    }


}
