package com.example.bookmanager_vinh.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.DanhSachNguoiDungAdapter;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungActivity extends AppCompatActivity {
    ListView lvNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;
    DanhSachNguoiDungAdapter danhSachNguoiDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        lvNguoiDung=findViewById(R.id.lvNguoiDung);
        nguoiDungDAO=new NguoiDungDAO(NguoiDungActivity.this);
        listNguoiDung=new ArrayList<>();
        listNguoiDung=nguoiDungDAO.getListNguoiDung();
       danhSachNguoiDungAdapter= new DanhSachNguoiDungAdapter(this,listNguoiDung);
        lvNguoiDung.setAdapter(danhSachNguoiDungAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.add_option:
                startActivity(new Intent(NguoiDungActivity.this, ThemNguoiDungActivity.class));
                break;
            case R.id.changePass_option:
                break;
            case R.id.logOut_option:
                startActivity(new Intent(NguoiDungActivity.this,LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("VONGDOI","onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("VONG DOI","onstop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("VONGDOI","onrestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("VONGDOI","ONRESUME");
        listNguoiDung.clear();
        listNguoiDung=nguoiDungDAO.getListNguoiDung();
        danhSachNguoiDungAdapter.changeDataset(listNguoiDung);

    }
}
