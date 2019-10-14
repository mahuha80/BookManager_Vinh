package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.HoaDonChiTiet;

import java.util.List;

public class LvThemHoaDonAdapter extends BaseAdapter {
    private Context context;
    private String maSach;
    private String tenSach;
    private String soLuongMua;
    private String gia;

//    public LvThemHoaDonAdapter(Context context, List<HoaDonChiTiet> listHoaDonChiTiet) {
//        this.context = context;
//        this.listHoaDonChiTiet = listHoaDonChiTiet;
//    }
//
//    @Override
//    public int getCount() {
//        return listHoaDonChiTiet.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        HoaDonHolder hoaDonHolder = null;
//        if (view == null) {
//            hoaDonHolder = new HoaDonHolder();
//            view = LayoutInflater.from(context).inflate(R.layout.lv_themhoadon, viewGroup, false);
//            hoaDonHolder.maSach = view.findViewById(R.id.tvMaSachTHD);
//            hoaDonHolder.soLuong = view.findViewById(R.id.tvSoLuongTHD);
//            hoaDonHolder.giaBia = view.findViewById(R.id.tvGiaBiaTHD);
//            hoaDonHolder.thanhTien = view.findViewById(R.id.tvMaSachTHD);
//            view.setTag(hoaDonHolder);
//        } else {
//            hoaDonHolder = (HoaDonHolder) view.getTag();
//        }
//        hoaDonHolder.maSach.setText("");
//        hoaDonHolder.soLuong.setText("");
//        hoaDonHolder.giaBia.setText("");
//        hoaDonHolder.thanhTien.setText("");
//
//        return view;
//    }
//
//    public class HoaDonHolder {
//        TextView maSach, soLuong, giaBia, thanhTien;
//        ImageView imgXoa;
//    }
}
