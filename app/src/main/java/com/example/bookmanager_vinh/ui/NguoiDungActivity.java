package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import com.example.bookmanager_vinh.adapter.LvDanhSachNguoiDungAdapter;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungActivity extends AppCompatActivity {
    ListView lvNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;
    LvDanhSachNguoiDungAdapter LVDanhSachNguoiDungAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        iconBack();
        init();
        boolean result = nguoiDungDAO.insertNguoiDung(new NguoiDung("admin", "admin", "0987395971", "Vinh NT", 1));
        if (result) {
            Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
        }
        LVDanhSachNguoiDungAdapter = new LvDanhSachNguoiDungAdapter(this, listNguoiDung);
        lvNguoiDung.setAdapter(LVDanhSachNguoiDungAdapter);
        final int role = nguoiDungDAO.getRoleViaUsername(NguoiDungDAO.usernameLogin);
        
            lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   if(role==1){
                       NguoiDung nguoiDungSelect = listNguoiDung.get(i);
                       intent = new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class);
                       Bundle bundle = new Bundle();
                       bundle.putSerializable("NguoiDung", nguoiDungSelect);
                       intent.putExtra("bundle", bundle);
                       startActivity(intent);
                   }else{
                       Toast.makeText(NguoiDungActivity.this, "Bạn không có quyền sửa ", Toast.LENGTH_SHORT).show();
                   }

                }
            });
       


    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    private void init() {
        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        listNguoiDung = new ArrayList<>();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_nguoidung:
                startActivity(new Intent(NguoiDungActivity.this, ThemNguoiDungActivity.class));
                break;
            case R.id.changepass_nguoidung:
                startActivity(new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class));
                break;
            case R.id.logout_nguoidung:
                Intent intentLogOut = new Intent(NguoiDungActivity.this, LoginActivity.class);
                intentLogOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogOut);
                NguoiDungActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("VONGDOI", "onstop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("VONG DOI", "onstop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("VONGDOI", "onrestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("VONGDOI", "ONRESUME");
        listNguoiDung.clear();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
//        Collections.reverse(listNguoiDung);
        LVDanhSachNguoiDungAdapter.changeDataset(listNguoiDung);

    }
}
