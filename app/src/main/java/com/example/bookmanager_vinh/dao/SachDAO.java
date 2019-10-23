package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH = "" +
            "CREATE TABLE Sach (masach text primary key,matheloai text,tensach text," +
            " tacgia text ,nxb text ,giabia double , soluong integer)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private NguoiDungDAO nguoiDungDAO;

    public SachDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        nguoiDungDAO = new NguoiDungDAO(context);
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

    public int updateSach(Sach sach) {
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
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maSach = cursor.getString(cursor.getColumnIndex("masach"));
            String matheloai = cursor.getString(cursor.getColumnIndex("matheloai"));
            String tensach = cursor.getString(cursor.getColumnIndex("tensach"));
            String tacgia = cursor.getString(cursor.getColumnIndex("tacgia"));
            String nxb = cursor.getString(cursor.getColumnIndex("nxb"));
            double giabia = cursor.getDouble(cursor.getColumnIndex("giabia"));
            int soluong = cursor.getInt(cursor.getColumnIndex("soluong"));
            listSach.add(new Sach(maSach, matheloai, tensach, tacgia, nxb, giabia, soluong));
            for (Sach sach : listSach) {
                Log.e("BUGG", sach.getMasach());
            }
            cursor.moveToNext();

        }
        return listSach;
    }

    public List<Sach> getSachTop10(String month) {
        List<Sach> listSach = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sql = "select masach,Sum(soluong) from HoaDonChiTiet inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%m', HoaDon.ngaymua) = ? group by masach order by soluong asc limit 10";
        Cursor cursor = db.rawQuery(sql, new String[]{month});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maSach = cursor.getString(0);
            String matheloai = "";
            String tensach ="";
            String tacgia = "";
            String nxb = "";
            double giabia = 0.0;
            int soluong = cursor.getInt(1);
            listSach.add(new Sach(maSach, matheloai, tensach, tacgia, nxb, giabia, soluong));
            cursor.moveToNext();
        }
        cursor.close();


        return listSach;

    }
}

