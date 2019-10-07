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
    int position;
    NguoiDung nguoiDungSelect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        iconBack();
        init();
        danhSachNguoiDungAdapter = new DanhSachNguoiDungAdapter(this, listNguoiDung);
        lvNguoiDung.setAdapter(danhSachNguoiDungAdapter);
        registerForContextMenu(lvNguoiDung);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                Log.e("ERROR1", position + "");
                nguoiDungSelect = listNguoiDung.get(i);
                Log.e("ERROR1", nguoiDungSelect + "");

            }
        });

    }

    private void iconBack() {
        ActionBar actionBar=getSupportActionBar();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
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
                startActivity(new Intent(NguoiDungActivity.this, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lv_context_nguoidung, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.xoa_nguoidung:
                int result = nguoiDungDAO.xoaNguoiDung(nguoiDungSelect.getUsername());
                if (result > 0) {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    listNguoiDung.remove(position);
                    onResume();

                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sua_nguoidung:
                Toast.makeText(this, "Sua", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("NguoiDung", nguoiDungSelect);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
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
        danhSachNguoiDungAdapter.changeDataset(listNguoiDung);

    }
}
