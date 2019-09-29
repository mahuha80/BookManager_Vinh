package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edMatKhau, edNhacLaiMatKhau;
    AutoCompleteTextView edTenDangNhap;
    Button btnDoiMatKhau;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> nguoiDungList;
    List<String> listTenNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        init();
        Intent intent = getIntent();
        String tenNguoiDung = intent.getStringExtra("tenNguoiDung");
        if(tenNguoiDung!=null){
            edTenDangNhap.setText(tenNguoiDung);
        }


        nguoiDungList = nguoiDungDAO.getAllNguoiDung();
        for (int i = 0; i < nguoiDungList.size(); i++) {
            String nguoiDung = nguoiDungList.get(i).getUsername();
            listTenNguoiDung.add(nguoiDung);
        }

        edTenDangNhap.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listTenNguoiDung));
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
        nguoiDungDAO = new NguoiDungDAO(DoiMatKhauActivity.this);
        nguoiDungList = new ArrayList<>();
        listTenNguoiDung = new ArrayList<>();
    }

    public void finishActivitiy(View view) {
        finish();
    }
}
