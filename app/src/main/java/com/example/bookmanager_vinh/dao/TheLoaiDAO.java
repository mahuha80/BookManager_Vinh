package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "" +
            "CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri integer)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertTheLoai(LoaiSach loaiSach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("matheloai", loaiSach.getMaTheLoai());
        contentValues.put("tentheloai", loaiSach.getTenTheLoai());
        contentValues.put("mota", loaiSach.getMoTa());
        contentValues.put("vitri", loaiSach.getVitri());
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public int deleteTheLoai(String matheloai) {
        return db.delete(TABLE_NAME, "matheloai=?", new String[]{matheloai});
    }

    public long updateTheLoai(LoaiSach loaiSach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("matheloai", loaiSach.getMaTheLoai());
        contentValues.put("tentheloai", loaiSach.getTenTheLoai());
        contentValues.put("mota", loaiSach.getMoTa());
        contentValues.put("vitri", loaiSach.getVitri());
        return db.update(TABLE_NAME, contentValues, "matheloai=?", new String[]{loaiSach.getMaTheLoai()});
    }

    public List<LoaiSach> getAllLoaiSach() {
        List<LoaiSach> listLoaiSach = new ArrayList<>();
        String queryGetAllLoaiSach = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(queryGetAllLoaiSach, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maTheLoai = cursor.getString(cursor.getColumnIndex("matheloai"));
            String tentheloai = cursor.getString(cursor.getColumnIndex("tentheloai"));
            String mota = cursor.getString(cursor.getColumnIndex("mota"));
            int vitri = cursor.getInt(cursor.getColumnIndex("vitri"));
            listLoaiSach.add(new LoaiSach(maTheLoai, tentheloai, mota, vitri));
            cursor.moveToNext();
        }
        return listLoaiSach;
    }

}
