package com.example.bookmanager_vinh.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanager_vinh.database.DatabaseHelper;

public class LoaiSachDAO {
    public static final String TABLE_NAME = "LoaiSach";
    public static final String SQL_NGUOI_DUNG = "" +
            "CREATE TABLE LoaiSach (matheloai text primary key, tentheloai text, mota text)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public LoaiSachDAO(Context context){
        dbHelper=new DatabaseHelper(context);
        db=dbHelper.getWritableDatabase();
    }

}
