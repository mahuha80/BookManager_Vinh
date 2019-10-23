package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.HoaDon;
import com.example.bookmanager_vinh.model.HoaDonChiTiet;

import java.text.SimpleDateFormat;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "" +
            "CREATE TABLE HoaDonChiTiet (mahdct integer primary key autoincrement, mahoadon text, masach text, soluong integer)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach", hoaDonChiTiet.getSach().getMasach());
        contentValues.put("soluong", hoaDonChiTiet.getSoLuongMua());
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public int updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach", hoaDonChiTiet.getSach().getMasach());
        contentValues.put("soluong", hoaDonChiTiet.getSoLuongMua());
        return db.update(TABLE_NAME, contentValues, "maHDCT=?", new String[]{hoaDonChiTiet.getMaHDCT() + ""});
    }

    public double getDoanhThuTheoNgay(){
        double doanhThu = 0;
        String sSQL="Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%d',HoaDon.ngaymua)=strftime('%d',date('now')))";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String sSQL="Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%m',HoaDon.ngaymua)=strftime('%m',date('now')))";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
        String sSQL="Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%Y',HoaDon.ngaymua)=strftime('%Y',date('now')))";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

}

