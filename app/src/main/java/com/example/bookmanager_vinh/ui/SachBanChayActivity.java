package com.example.bookmanager_vinh.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvQLySachAdapter;
import com.example.bookmanager_vinh.dao.SachDAO;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachBanChayActivity extends AppCompatActivity {
    LvQLySachAdapter lvQLySachAdapter;
    SachDAO sachDAO;
    List<Sach> listSach;
    EditText edSachBanChay;
    Button btnSachBanChay;
    ListView lvSachBanChay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        edSachBanChay = findViewById(R.id.edSachBanChay);
        btnSachBanChay = findViewById(R.id.btnSachBanChay);
        lvSachBanChay = findViewById(R.id.lvSachBanChay);
        iconBack();
        sachDAO = new SachDAO(this);
        listSach = new ArrayList<>();
        if (edSachBanChay.getText().toString().equals("")) {
            Toast.makeText(this, "Vui lòng điền đầy đủ các trường ", Toast.LENGTH_SHORT).show();
            return;
        } else {
            btnSachBanChay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String month = edSachBanChay.getText().toString();
                    listSach = sachDAO.getSachTop10(month);
                    if (listSach.size() > 0) {
                        lvQLySachAdapter = new LvQLySachAdapter(SachBanChayActivity.this, listSach);
                        lvSachBanChay.setAdapter(lvQLySachAdapter);
                    } else {
                        listSach.clear();
                        lvQLySachAdapter = new LvQLySachAdapter(SachBanChayActivity.this, listSach);
                        lvSachBanChay.setAdapter(lvQLySachAdapter);
                    }

                }
            });
        }
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
