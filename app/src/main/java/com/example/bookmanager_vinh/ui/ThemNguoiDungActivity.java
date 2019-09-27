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

import java.util.ArrayList;
import java.util.List;

public class ThemNguoiDungActivity extends AppCompatActivity {
    //btnThemNguoiDung,btnHienDanhSachNguoiDung;
    Button btnHuy;
    EditText edTenDangNhap, edMatKhau, edNhapLaiMatKhau, edSoDienThoai, edHoVaTen;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;
    List<String> listTenNguoiDung;

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
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
        for (int i = 0; i < listNguoiDung.size(); i++) {
            String s = listNguoiDung.get(i).getUsername();
            listTenNguoiDung.add(s);
        }

        String username = edTenDangNhap.getText().toString();
        String pass = edMatKhau.getText().toString();
        String rePass = edNhapLaiMatKhau.getText().toString();
        String phone = edSoDienThoai.getText().toString();
        String fullname = edHoVaTen.getText().toString();
        if (username.equals("") || pass.equals("") && phone.equals("") && fullname.equals("")) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ các dòng !", Toast.LENGTH_SHORT).show();
            return;
        } else if (!pass.equals(rePass)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu trùng nhau !", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < listTenNguoiDung.size(); i++) {
            if (username.equalsIgnoreCase(listTenNguoiDung.get(i))) {
                Toast.makeText(this, "Tên đăng nhập đã tồn tại trong hệ thống !", Toast.LENGTH_SHORT).show();
                return;
            }
        }
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
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhapLaiMatKhau = findViewById(R.id.edNhacLaiMatKhau);
        edSoDienThoai = findViewById(R.id.edPhone);
        edHoVaTen = findViewById(R.id.edHoVaTen);
        listNguoiDung = new ArrayList<>();
        listTenNguoiDung = new ArrayList<>();

    }


}
