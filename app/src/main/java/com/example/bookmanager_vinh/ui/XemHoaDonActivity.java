package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvXemHoaDonAdapter;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class XemHoaDonActivity extends AppCompatActivity {
    EditText edMaHoaDon, edNgayMua;
    List<Sach> listSach;
    ArrayList<Integer> soLuong;
    ListView lvXemHoaDon;
    LvXemHoaDonAdapter lvXemHoaDonAdapter;
    TextView tvTongTien;
    Button btnThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hoa_don);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null) {
            String maHoaDon = bundle.getString("mahoadon");
            String ngayMua = bundle.getString("ngaymua");
            edMaHoaDon.setText(maHoaDon);
            edNgayMua.setText(ngayMua);
            edMaHoaDon.setEnabled(false);
            edNgayMua.setEnabled(false);
            listSach = (List<Sach>) intent.getSerializableExtra("list");
            soLuong = intent.getIntegerArrayListExtra("soLuong");
            lvXemHoaDonAdapter = new LvXemHoaDonAdapter(this, listSach, soLuong);
            lvXemHoaDon.setAdapter(lvXemHoaDonAdapter);
        }
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void init() {
        edMaHoaDon = findViewById(R.id.edMaHDXHD);
        edNgayMua = findViewById(R.id.edNgayMuaXHD);
        listSach = new ArrayList<>();
        soLuong = new ArrayList<>();
        lvXemHoaDon = findViewById(R.id.lvXemHoaDon);
        tvTongTien=findViewById(R.id.tvTongTien);
        btnThanhToan=findViewById(R.id.btnThanhToan);
    }
}
