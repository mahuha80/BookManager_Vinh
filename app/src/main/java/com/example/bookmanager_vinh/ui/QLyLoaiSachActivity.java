package com.example.bookmanager_vinh.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.QLyLoaiSachAdapter;
import com.example.bookmanager_vinh.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class QLyLoaiSachActivity extends AppCompatActivity {
    private ListView lvQuanLyLoaiSach;
    private List<LoaiSach> listLoaiSach;
    private QLyLoaiSachAdapter qLyLoaiSachAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sach);
        lvQuanLyLoaiSach = findViewById(R.id.lvQLyLoaiSach);
        listLoaiSach = new ArrayList<>();
        listLoaiSach.add(new LoaiSach("vinh", "vinh", "vinh", 8));
        qLyLoaiSachAdapter=new QLyLoaiSachAdapter(this,listLoaiSach);
        lvQuanLyLoaiSach.setAdapter(qLyLoaiSachAdapter);


    }


}
