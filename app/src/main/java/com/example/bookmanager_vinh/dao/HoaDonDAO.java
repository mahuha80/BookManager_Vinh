package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDAO {
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON = "" +
            "CREATE TABLE HoaDon (mahoadon text primary key, ngaymua date)";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDon(HoaDon hoaDon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDon.getMaHoaDon());
        contentValues.put("ngaymua", simpleDateFormat.format(hoaDon.getNgayMua()));
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public int updateHoaDon(HoaDon hoaDon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDon.getMaHoaDon());
        contentValues.put("ngaymua", simpleDateFormat.format(hoaDon.getNgayMua()));
        return db.update(TABLE_NAME, contentValues, "mahoadon=?", new String[]{hoaDon.getMaHoaDon()});
    }

    public int deleteHoaDon(String mahoadon) {
        return db.delete(TABLE_NAME, "mahoadon=?", new String[]{mahoadon});
    }

    public List<HoaDon> getAllHoaDon() throws ParseException {
        List<HoaDon> listHoaDon = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahoadon = cursor.getString(cursor.getColumnIndex("mahoadon"));
            Date ngaymua = simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex("ngaymua")));
            HoaDon hoaDon = new HoaDon(mahoadon, ngaymua);
            listHoaDon.add(hoaDon);
            cursor.moveToNext();
        }
        return listHoaDon;

    }
}
