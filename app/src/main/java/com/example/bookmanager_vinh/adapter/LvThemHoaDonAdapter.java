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
    private List<HoaDonChiTiet> listHoaDonChiTiet;

    public LvThemHoaDonAdapter(Context context, List<HoaDonChiTiet> listHoaDonChiTiet) {
        this.context = context;
        this.listHoaDonChiTiet = listHoaDonChiTiet;
    }

    //
    @Override
    public int getCount() {
        return listHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        HoaDonHolder hoaDonHolder = null;
        if (view == null) {
            hoaDonHolder = new HoaDonHolder();
            view = LayoutInflater.from(context).inflate(R.layout.lv_themhoadon, viewGroup, false);
            hoaDonHolder.tvMaSach = view.findViewById(R.id.tvMaSachTHD);
            hoaDonHolder.tvSoLuong = view.findViewById(R.id.tvSoLuongTHD);
            hoaDonHolder.tvGiaBia = view.findViewById(R.id.tvGiaBiaTHD);
            hoaDonHolder.tvThanhTien = view.findViewById(R.id.tvThanhTienTHD);
            hoaDonHolder.imgXoa = view.findViewById(R.id.imgXoaTHD);
            view.setTag(hoaDonHolder);
        } else {
            hoaDonHolder = (HoaDonHolder) view.getTag();
        }
        hoaDonHolder.tvMaSach.setText("Mã sách " + listHoaDonChiTiet.get(i).getSach().getMasach());
        hoaDonHolder.tvSoLuong.setText("Tên sách " + listHoaDonChiTiet.get(i).getSach().getTensach() + "");
        hoaDonHolder.tvGiaBia.setText("Số lượng mua " + listHoaDonChiTiet.get(i).getSoLuongMua() + "");
        int soLuong = listHoaDonChiTiet.get(i).getSoLuongMua();
        Double giatien = listHoaDonChiTiet.get(i).getSach().getGiabia();
        hoaDonHolder.tvThanhTien.setText("Giá " + soLuong * giatien + "");
        hoaDonHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listHoaDonChiTiet.remove(i);
                notifyDataSetChanged();
            }
        });


        return view;
    }

    public class HoaDonHolder {
        TextView tvMaSach, tvSoLuong, tvGiaBia, tvThanhTien;
        ImageView imgXoa;
    }

}
