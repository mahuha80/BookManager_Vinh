package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;

public class XemHoaDonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_hoa_don);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Bundle");


    }
}
