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

    public List<ThongKe> getHoaDonTheoNgay(String ngay) throws ParseException {
        List<ThongKe> listThongKeTheoNgay = new ArrayList<>();
        String sSQL = "Select HoaDonChiTiet.mahoadon,HoaDon.ngaymua ,sum((Sach.giabia*HoaDonChiTiet.soluong)) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%Y %m %d',HoaDon.ngaymua)=strftime('%Y %m %d',?) group by HoaDonChiTiet.mahoadon,HoaDon.ngaymua";
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

    public List<ThongKe> getTongDoanhThuMoiThangTrongNam() {
        List<ThongKe> listThongKeTheoNgay = new ArrayList<>();
        Cursor c = null;
        String sSQL = "Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%m',HoaDon.ngaymua)=?)";
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                String month = "0" + i;
                c = db.rawQuery(sSQL, new String[]{month});
            } else {
                c = db.rawQuery(sSQL, new String[]{String.valueOf(i)});
            }
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                double tongtien = 0;
                try {
                    tongtien = c.getDouble(0);
                } catch (Exception e) {
                    tongtien = 0;
                }
                ThongKe thongKe = new ThongKe(tongtien + "");
                listThongKeTheoNgay.add(thongKe);
                c.moveToNext();
            }

        }
        return listThongKeTheoNgay;
    }
    public List<ThongKe> getHoaDonTheoKhoangThoiGian(String mahoadon) {
//        select HoaDonChiTiet.mahoadon,HoaDon.ngaymua,(HoaDonChiTiet.soluong*Sach.giabia) as tongtien from HoaDonChiTiet inner join HoaDon on HoaDonChiTiet.mahoadon=HoaDon.mahoadon inner join HoaDonChiTiet on HoaDonChiTiet.masach=Sach.masach where strftime('%Y %m %d',HoaDon.ngaymua) between strftime('%Y %m %d','2019-09-23') and strftime('%Y %m %d','2019-10-22')
        List<ThongKe> listThongKe = new ArrayList<>();
        String sSQL = "select HoaDonChiTiet.mahdct,HoaDonChiTiet.masach,HoaDonChiTiet.soluong,Sach.giabia from HoaDonChiTiet inner join Sach on Sach.masach=HoaDonChiTiet.masach where HoaDonChiTiet.mahoadon=?";
        Cursor cursor = db.rawQuery(sSQL, new String[]{mahoadon});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahdct = cursor.getString(0);
            String masach = cursor.getString(1);
            String soluong = cursor.getString(2);
            String giabia = cursor.getString(3);
            ThongKe thongKe = new ThongKe(mahdct, masach, soluong, giabia);
            listThongKe.add(thongKe);
            cursor.moveToNext();
        }
        return listThongKe;
    }


    public List<ThongKe> getChiTietHoaDonTheoMaHoaDon(String mahoadon) {
        List<ThongKe> listThongKe = new ArrayList<>();
        String sSQL = "select HoaDonChiTiet.mahdct,HoaDonChiTiet.masach,HoaDonChiTiet.soluong,Sach.giabia from HoaDonChiTiet inner join Sach on Sach.masach=HoaDonChiTiet.masach where HoaDonChiTiet.mahoadon=?";
        Cursor cursor = db.rawQuery(sSQL, new String[]{mahoadon});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahdct = cursor.getString(0);
            String masach = cursor.getString(1);
            String soluong = cursor.getString(2);
            String giabia = cursor.getString(3);
            ThongKe thongKe = new ThongKe(mahdct, masach, soluong, giabia);
            listThongKe.add(thongKe);
            cursor.moveToNext();
        }
        return listThongKe;
    }


//    public double getDoanhThuTheoThang() {
//        double doanhThu = 0;
//        String sSQL = "Select Sum(tongtien) from (Select (Sach.giabia*HoaDonChiTiet.soluong) as tongtien from HoaDonChiTiet inner join Sach on HoaDonChiTiet.masach=Sach.masach inner join HoaDon on HoaDon.mahoadon=HoaDonChiTiet.mahoadon where strftime('%m',HoaDon.ngaymua)=strftime('%m',date('now')))";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast() == false) {
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//    }

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

