package com.example.bookmanager_vinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
        lvQuanLySach=findViewById(R.id.lv_QLySach);
        listSach=new ArrayList<>();
        for(int i=0;i<20;i++){
            Sach sach=new Sach("vinh"+i,10+i);
            listSach.add(sach);
        }
        QLySachAdapter qLySachAdapter=new QLySachAdapter(this,listSach);
        lvQuanLySach.setAdapter(qLySachAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_quanlysach, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.themSach:
                startActivity(new Intent(QLySachActivity.this,ThemSachActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
