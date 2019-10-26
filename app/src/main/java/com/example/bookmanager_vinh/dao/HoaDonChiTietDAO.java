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
import com.example.bookmanager_vinh.model.Sach;
import com.example.bookmanager_vinh.model.ThongKe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "" +
            "CREATE TABLE HoaDonChiTiet (mahdct integer primary key autoincrement, mahoadon text, masach text, soluong integer)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


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

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sSQL = "Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%d',HoaDon.ngaymua)=strftime('%d',date('now')))";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public List getTungMaDoanhThuTheoNgay(String ngay) throws ParseException {
        List<ThongKe> listThongKeTheoNgay = new ArrayList<>();
        String sSQL = "Select HoaDonChiTiet.mahoadon,HoaDon.ngaymua ,sum((Sach.giabia*HoaDonChiTiet.soluong)) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%d',HoaDon.ngaymua)=strftime('%d',?) group by HoaDonChiTiet.mahoadon,HoaDon.ngaymua";
        Cursor c = db.rawQuery(sSQL, new String[]{ngay});
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            String mahoadon = c.getString(c.getColumnIndex("mahoadon"));
            String ngaymua = c.getString(c.getColumnIndex("ngaymua"));
            String tongtien = c.getString(c.getColumnIndex("tongtien"));
            ThongKe thongKe = new ThongKe(mahoadon, ngaymua, tongtien);
            listThongKeTheoNgay.add(thongKe);
            c.moveToNext();
        }
//        Select HoaDonChiTiet.mahoadon,HoaDon.ngaymua,(Sach.giabia*HoaDonChiTiet.soluong) as tongtien,HoaDonChiTiet.masach from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%Y %m %d',HoaDon.ngaymua)=strftime('%Y %m %d','2019-10-23')
        c.close();
        return listThongKeTheoNgay;
    }

    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String sSQL = "Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%m',HoaDon.ngaymua)=strftime('%m',date('now')))";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String sSQL = "Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%Y',HoaDon.ngaymua)=strftime('%Y',date('now')))";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

}

