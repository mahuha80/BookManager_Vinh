package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.QLySachAdapter;

import java.util.ArrayList;
import java.util.List;

public class QLySachActivity extends AppCompatActivity {
    List<Sach> listSach;
    ListView lvQuanLySach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        iconBack();
        lvQuanLySach = findViewById(R.id.lv_QLySach);
        listSach = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Sach sach = new Sach("vinh" + i, 10 + i);
            listSach.add(sach);
        }
        QLySachAdapter qLySachAdapter = new QLySachAdapter(this, listSach);
        lvQuanLySach.setAdapter(qLySachAdapter);
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quanlisach, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.themSach:
                startActivity(new Intent(QLySachActivity.this, ThemSachActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
