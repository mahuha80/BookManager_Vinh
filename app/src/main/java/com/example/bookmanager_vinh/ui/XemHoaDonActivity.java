package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvXemHoaDonAdapter;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.dao.HoaDonDAO;
import com.example.bookmanager_vinh.model.HoaDon;
import com.example.bookmanager_vinh.model.HoaDonChiTiet;
import com.example.bookmanager_vinh.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XemHoaDonActivity extends AppCompatActivity {
    EditText edMaHoaDon, edNgayMua;
    List<Sach> listSach;
    ArrayList<Integer> soLuong;
    ListView lvXemHoaDon;
    LvXemHoaDonAdapter lvXemHoaDonAdapter;
    TextView tvTongTien;
    Button btnThanhToan;
    List<HoaDonChiTiet> listHoaDonChiTiet;
    String maHoaDon, ngayMua;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonDAO hoaDonDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hoa_don);
        init();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null) {
            maHoaDon = bundle.getString("mahoadon");
            ngayMua = bundle.getString("ngaymua");
            edMaHoaDon.setText(maHoaDon);
            edNgayMua.setText(ngayMua);
            edMaHoaDon.setEnabled(false);
            edNgayMua.setEnabled(false);
            listSach = (List<Sach>) intent.getSerializableExtra("list");
            soLuong = intent.getIntegerArrayListExtra("soLuong");
            lvXemHoaDonAdapter = new LvXemHoaDonAdapter(this, listSach, soLuong);
            lvXemHoaDon.setAdapter(lvXemHoaDonAdapter);
            setTongTien();
        }
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                HoaDon hoaDon = new HoaDon(maHoaDon, date);
                for (int i = 0; i < listSach.size(); i++) {
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(hoaDon, listSach.get(i), soLuong.get(i));
                    long result1 = hoaDonDAO.insertHoaDon(hoaDon);
                    long result = hoaDonChiTietDAO.insertHoaDonChiTiet(hoaDonChiTiet);
                    if (result > 0) {
                        Toast.makeText(XemHoaDonActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(XemHoaDonActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    private void setTongTien() {
        double tongTien = 0;
        for (int i = 0; i < listSach.size(); i++) {
            tongTien += listSach.get(i).getGiabia() * soLuong.get(i);
        }
        tvTongTien.setText("Tổng tiền : " + tongTien + "");
    }

    private void init() {
        edMaHoaDon = findViewById(R.id.edMaHDXHD);
        edNgayMua = findViewById(R.id.edNgayMuaXHD);
        listSach = new ArrayList<>();
        soLuong = new ArrayList<>();
        lvXemHoaDon = findViewById(R.id.lvXemHoaDon);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        listHoaDonChiTiet = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
    }
}
