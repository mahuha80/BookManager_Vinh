package com.example.bookmanager_vinh.ui;

import android.content.Intent;
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
    String itemSelected = "";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        listNguoiDung = new ArrayList<>();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
//        Collections.reverse(listNguoiDung);
        danhSachNguoiDungAdapter = new DanhSachNguoiDungAdapter(this, listNguoiDung);
        lvNguoiDung.setAdapter(danhSachNguoiDungAdapter);
        registerForContextMenu(lvNguoiDung);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelected = listNguoiDung.get(i).getUsername();
                position = i;
                Log.e("ERROR1", itemSelected + "");
                Log.e("ERROR1", position + "");

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_option:
                startActivity(new Intent(NguoiDungActivity.this, ThemNguoiDungActivity.class));
                break;
            case R.id.changePass_option:
                startActivity(new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class));
                break;
            case R.id.logOut_option:
                startActivity(new Intent(NguoiDungActivity.this, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_register, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.xoa:
                int result = nguoiDungDAO.xoaNguoiDung(itemSelected);
                if (result > 0) {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    listNguoiDung.remove(position);
                    onResume();

                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sua:
                Toast.makeText(this, "Sua", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NguoiDungActivity.this,DoiMatKhauActivity.class);
                intent.putExtra("tenNguoiDung",itemSelected);
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
