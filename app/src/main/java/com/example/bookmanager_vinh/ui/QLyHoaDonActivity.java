package com.example.bookmanager_vinh.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvQuanLyHoaDonAdapter;

public class QLyHoaDonActivity extends AppCompatActivity {
    ListView lvHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        iconBack();
        lvHoaDon=findViewById(R.id.lvHoaDon);
        lvHoaDon.setAdapter(new LvQuanLyHoaDonAdapter(this));
    }

    private void iconBack() {
        ActionBar actionBar=getSupportActionBar();
        Drawable drawable=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
