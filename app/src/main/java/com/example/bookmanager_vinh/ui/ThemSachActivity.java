package com.example.bookmanager_vinh.ui;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
//import com.example.bookmanager_vinh.adapter.ThemSachAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    Spinner spinner;
//    List<Sach> listSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        spinner = findViewById(R.id.spTheLoaiSach);
//        listSach = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Sach sach = new Sach("Lap trinh java " + i, i);
//            listSach.add(sach);
//        }
//        ThemSachAdapter themSachAdapter = new ThemSachAdapter(this, listSach);
//        spinner.setAdapter(themSachAdapter);

    }
}
