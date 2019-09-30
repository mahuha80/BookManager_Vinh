package com.example.bookmanager_vinh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanager_vinh.database.DatabaseHelper;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "" +
            "CREATE TABLE NguoiDung (username text primary key, password text, phone text ,hoten text)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertNguoiDung(NguoiDung nd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nd.getUsername());
        contentValues.put("password", nd.getPass());
        contentValues.put("phone", nd.getPhone());
        contentValues.put("hoten", nd.getFullname());
        long result = db.insert(TABLE_NAME, null, contentValues);
        try {
            if (result == -1) {
                return false;
            }

        } catch (Exception ex) {
            Log.e("TAG", ex.toString());
            return false;
        }

        return true;
    }

    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
            list.add(new NguoiDung(username, password, phone, hoten));
            cursor.moveToNext();
        }
        return list;
    }

    public int xoaNguoiDung(String tenNguoiDung) {
        if (tenNguoiDung.equals("admin")) return -1;
        return db.delete(TABLE_NAME, "username=?", new String[]{tenNguoiDung});
    }

    public int updateNguoiDung(NguoiDung nd) {
        if (nd.getUsername().equals("admin")) return -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nd.getUsername());
        contentValues.put("password", nd.getPass());
        contentValues.put("phone", nd.getPhone());
        contentValues.put("hoten", nd.getFullname());
        return db.update(TABLE_NAME, contentValues, "username=?", new String[]{nd.getUsername()});
    }
    public boolean isLogin(NguoiDung nd){
        String SQL="SELECT username, password from NguoiDung where username=? and password=?";
        Cursor cursor=db.rawQuery(SQL,new String[]{nd.getUsername(),nd.getPass()});
        if(cursor.moveToFirst()){
            return true;
        }
        Log.e("ERROR1",cursor.toString()+"");


        return false;
    }



}
