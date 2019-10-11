package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    NguoiDung nguoiDung = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        init();
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            nguoiDung = (NguoiDung) bundle.getSerializable("NguoiDung");
            edTenDangNhap.setText(nguoiDung.getUsername());
            edTenDangNhap.setEnabled(false);
        }


        nguoiDungList = nguoiDungDAO.getAllNguoiDung();
        for (int i = 0; i < nguoiDungList.size(); i++) {
            String tenNguoiDung = nguoiDungList.get(i).getUsername();
            listTenNguoiDung.add(tenNguoiDung);
        }

        edTenDangNhap.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listTenNguoiDung));
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edMatKhau.getText().toString().equals("") || edNhacLaiMatKhau.getText().toString().equals("")) {
                    Toast.makeText(DoiMatKhauActivity.this, "Vui lòng không để trống !", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!edMatKhau.getText().toString().equals(edNhacLaiMatKhau.getText().toString())) {
                    Toast.makeText(DoiMatKhauActivity.this, "Vui lòng nhập mật khẩu trùng nhau !", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if ((nguoiDungDAO.isChangePassword(new NguoiDung(edTenDangNhap.getText().toString(), edMatKhau.getText().toString())))) {
                        Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu không thành công !", Toast.LENGTH_SHORT).show();

                    }
                }


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
