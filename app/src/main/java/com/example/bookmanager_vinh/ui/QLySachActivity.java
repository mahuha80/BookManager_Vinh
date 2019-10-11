package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.QLySachAdapter;
import com.example.bookmanager_vinh.dao.SachDAO;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

//import com.example.bookmanager_vinh.adapter.QLySachAdapter;

public class QLySachActivity extends AppCompatActivity {
    List<Sach> listSach;
    ListView lvQuanLySach;
    SachDAO sachDAO;
    QLySachAdapter qLySachAdapter;
    Sach sach;
    int position = 0;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        iconBack();
        listSach = new ArrayList<>();
        sachDAO = new SachDAO(this);
        lvQuanLySach = findViewById(R.id.lv_QLySach);
        listSach = sachDAO.getAllSach();
        qLySachAdapter = new QLySachAdapter(this, listSach);

        lvQuanLySach.setAdapter(qLySachAdapter);
        registerForContextMenu(lvQuanLySach);
        lvQuanLySach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                sach = listSach.get(i);
                Log.e("BUGG",sach.getMasach());

            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lv_context_qlysach, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.xoa_sach:
                int result = sachDAO.deleteSach(sach.getMasach());
                if (result > 0) {
                    Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    onResume();
                } else {
                    Toast.makeText(this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.sua_sach:
                intent=new Intent(this,SuaSachActivity.class);
                bundle=new Bundle();
                bundle.putSerializable("Sach",sach);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
              break;
        }
        return super.onContextItemSelected(item);

    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listSach.clear();
        listSach = sachDAO.getAllSach();
        qLySachAdapter.ondatasetchanged(listSach);
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
