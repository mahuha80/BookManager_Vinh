package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH = "" +
            "CREATE TABLE Sach (masach text primary key,matheloai text,tensach text," +
            " tacgia text ,nxb text ,giabia double , soluong number)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertSach(Sach sach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach", sach.getMasach());
        contentValues.put("matheloai", sach.getMatheloai());
        contentValues.put("tensach", sach.getTensach());
        contentValues.put("tacgia", sach.getTacgia());
        contentValues.put("nxb", sach.getNxb());
        contentValues.put("giabia", sach.getGiabia());
        contentValues.put("soluong", sach.getSoluong());
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public int deleteSach(String masach) {
        return db.delete(TABLE_NAME, "masach=?", new String[]{masach});
    }

    public long updateSach(Sach sach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach", sach.getMasach());
        contentValues.put("matheloai", sach.getMatheloai());
        contentValues.put("tensach", sach.getTensach());
        contentValues.put("tacgia", sach.getTacgia());
        contentValues.put("nxb", sach.getNxb());
        contentValues.put("giabia", sach.getGiabia());
        contentValues.put("soluong", sach.getSoluong());
        return db.update(TABLE_NAME, contentValues, "masach=?", new String[]{sach.getMasach()});
    }

    public List<Sach> getAllSach() {
        List<Sach> listSach = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maSach = cursor.getString(cursor.getColumnIndex("masach"));
            String matheloai = cursor.getString(cursor.getColumnIndex("matheloai"));
            String tensach = cursor.getString(cursor.getColumnIndex("tensach"));
            String tacgia = cursor.getString(cursor.getColumnIndex("tacgia"));
            String nxb = cursor.getString(cursor.getColumnIndex("nxb"));
            String giabia = cursor.getString(cursor.getColumnIndex("giabia"));
            String soluong = cursor.getString(cursor.getColumnIndex("soluong"));
            listSach.add(new Sach(maSach, matheloai, tensach, tacgia, nxb, giabia, soluong));
            for(Sach sach:listSach){
                Log.e("BUGG",sach.getMasach());
            }
            cursor.moveToNext();

        }
        return listSach;
    }
}

