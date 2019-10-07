package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.example.bookmanager_vinh.adapter.QLyLoaiSachAdapter;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;
import com.example.bookmanager_vinh.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class QLyLoaiSachActivity extends AppCompatActivity {
    private ListView lvQuanLyLoaiSach;
    private List<LoaiSach> listLoaiSach;
    private QLyLoaiSachAdapter qLyLoaiSachAdapter;
    private TheLoaiDAO theLoaiDAO;
    int position;
    LoaiSach loaiSach=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sach);
        iconBack();
        theLoaiDAO = new TheLoaiDAO(this);
        lvQuanLyLoaiSach = findViewById(R.id.lvQLyLoaiSach);
        listLoaiSach = new ArrayList<>();
        listLoaiSach = theLoaiDAO.getAllLoaiSach();
        qLyLoaiSachAdapter = new QLyLoaiSachAdapter(this, listLoaiSach);
        lvQuanLyLoaiSach.setAdapter(qLyLoaiSachAdapter);
        registerForContextMenu(lvQuanLyLoaiSach);
        lvQuanLyLoaiSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position=i;
                loaiSach=listLoaiSach.get(i);

            }
        });

    }

    private void iconBack() {
        ActionBar actionBar=getSupportActionBar();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lv_quanliloaisach, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.xoa_loaisach:
        if(theLoaiDAO.deleteTheLoai(loaiSach.getMaTheLoai())>0){
            Toast.makeText(this, "Xóa thành công loại sách !", Toast.LENGTH_SHORT).show();
            listLoaiSach.remove(position);
            onResume();
        }else{
            Toast.makeText(this, "Xóa không thành công loại sách !", Toast.LENGTH_SHORT).show();
        }
                break;
            case R.id.sua_loaisach:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_qlyloaisach, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them_loaisach:
                startActivity(new Intent(QLyLoaiSachActivity.this, ThemTheLoaiActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        qLyLoaiSachAdapter.dataSetChange(theLoaiDAO.getAllLoaiSach());
    }
}
