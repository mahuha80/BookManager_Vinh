package com.example.bookmanager_vinh.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.DanhSachNguoiDungAdapter;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;

public class NguoiDungActivity extends AppCompatActivity {
    ListView lvNguoiDung;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nguoiDungDAO=new NguoiDungDAO(NguoiDungActivity.this);
        lvNguoiDung=findViewById(R.id.lvNguoiDung);
        lvNguoiDung.setAdapter(new DanhSachNguoiDungAdapter(this,nguoiDungDAO.getListNguoiDung()));
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
}
