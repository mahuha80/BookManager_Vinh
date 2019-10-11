package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;
import com.example.bookmanager_vinh.model.LoaiSach;

public class SuaTheLoaiActivity extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;
    Button btnThem,btnSua;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_the_loai);
        init();
        intent=getIntent();
        bundle=intent.getBundleExtra("Bundle");
        if(bundle!=null){
            LoaiSach loaiSach= (LoaiSach) bundle.getSerializable("LoaiSach");
            edMaTheLoai.setText(loaiSach.getMaTheLoai());
            edMoTa.setText(loaiSach.getMoTa());
            edTenTheLoai.setText(loaiSach.getTenTheLoai());
            edViTri.setText(loaiSach.getVitri()+"");
            edMaTheLoai.setEnabled(false);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maTheLoai=edMaTheLoai.getText().toString();
                String moTa=edMoTa.getText().toString();
                String tenTheLoai=edTenTheLoai.getText().toString();
                int viTri= Integer.parseInt(edViTri.getText().toString());
                LoaiSach loaiSach=new LoaiSach(maTheLoai,tenTheLoai,moTa,viTri);
               long result= theLoaiDAO.updateTheLoai(loaiSach);
               if(result>0){
                   Toast.makeText(SuaTheLoaiActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(SuaTheLoaiActivity.this, "Khong thanh cong", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    private void init() {
        edMaTheLoai=findViewById(R.id.edMaTheLoai);
        edTenTheLoai=findViewById(R.id.edTenTheLoai);
        edViTri=findViewById(R.id.edViTri);
        btnThem=findViewById(R.id.btnSua);
        edMoTa=findViewById(R.id.edMoTa);
        theLoaiDAO=new TheLoaiDAO(this);
        btnSua=findViewById(R.id.btnSua);
    }
}
