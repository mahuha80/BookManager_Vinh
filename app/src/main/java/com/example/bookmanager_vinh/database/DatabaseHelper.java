package com.example.bookmanager_vinh.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int version = 2;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        sqLiteDatabase.execSQL(TheLoaiDAO.SQL_THE_LOAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NguoiDungDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TheLoaiDAO.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
