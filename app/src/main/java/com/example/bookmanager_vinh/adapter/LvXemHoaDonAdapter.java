package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LvXemHoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<Sach> listSach;
    private ArrayList<Integer> soLuong;

    public LvXemHoaDonAdapter(Context context, List<Sach> listSach, ArrayList<Integer> soLuong) {
        this.context = context;
        this.listSach = listSach;
        this.soLuong = soLuong;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        XemHoaDonHolder xemHoaDonHolder = null;
        if (convertView == null) {
            xemHoaDonHolder = new XemHoaDonHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_xemhoadon, parent, false);
            xemHoaDonHolder.tvMaSach = convertView.findViewById(R.id.tvMaSachXHD);
            xemHoaDonHolder.tvSoLuong = convertView.findViewById(R.id.tvSoLuongXHD);
            xemHoaDonHolder.tvGiaBia = convertView.findViewById(R.id.tvGiaBiaXHD);
            xemHoaDonHolder.tvThanhTien = convertView.findViewById(R.id.tvThanhTienXHD);
//            xemHoaDonHolder.imgXoa = convertView.findViewById(R.id.imgXoaXHD);
            convertView.setTag(xemHoaDonHolder);
        } else {
            xemHoaDonHolder = (XemHoaDonHolder) convertView.getTag();
        }
        xemHoaDonHolder.tvGiaBia.setText("Giá bìa : " + listSach.get(position).getGiabia() + "");

        xemHoaDonHolder.tvSoLuong.setText("Số lượng : " + soLuong.get(position) + "");
        xemHoaDonHolder.tvMaSach.setText("Mã sách : " + listSach.get(position).getMasach());
        double giatien = listSach.get(position).getGiabia() * soLuong.get(position);
        xemHoaDonHolder.tvThanhTien.setText("Thành tiền : " + giatien + "");
//
//        xemHoaDonHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listSach.remove(position);
//                soLuong.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        return convertView;
    }

    public class XemHoaDonHolder {
        TextView tvMaSach, tvSoLuong, tvGiaBia, tvThanhTien;
//        ImageView imgXoa;
    }

}
