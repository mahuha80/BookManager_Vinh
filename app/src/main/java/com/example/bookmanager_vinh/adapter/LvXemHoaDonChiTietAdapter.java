package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.ThongKe;

import java.util.List;

public class LvXemHoaDonChiTietAdapter extends BaseAdapter {
    private Context context;
    private List<ThongKe> listThongKe;

    public LvXemHoaDonChiTietAdapter(Context context, List<ThongKe> listThongKe) {
        this.context = context;
        this.listThongKe = listThongKe;
    }

    @Override
    public int getCount() {
        return listThongKe.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        XemHoaDonChiTietHolder xemHoaDonChiTietHolder = null;
        if (convertView == null) {
            xemHoaDonChiTietHolder=new XemHoaDonChiTietHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_hoadonchitiet, parent, false);
            xemHoaDonChiTietHolder.tvmahdct=convertView.findViewById(R.id.tvmahdct);
            xemHoaDonChiTietHolder.tvmasach=convertView.findViewById(R.id.tvMaSachmhdct);
            xemHoaDonChiTietHolder.tvsoluong=convertView.findViewById(R.id.tvsoluongmhdct);
            xemHoaDonChiTietHolder.tvgiabia=convertView.findViewById(R.id.tvgiabiamhdct);
            convertView.setTag(xemHoaDonChiTietHolder);
        }else{
           xemHoaDonChiTietHolder= (XemHoaDonChiTietHolder) convertView.getTag();
        }
        xemHoaDonChiTietHolder.tvgiabia.setText("Giá bìa: "+listThongKe.get(position).getGiabia());
        xemHoaDonChiTietHolder.tvsoluong.setText("Số lượng: "+listThongKe.get(position).getSoluong());
        xemHoaDonChiTietHolder.tvmasach.setText("Mã sách: "+listThongKe.get(position).getMasach());
        xemHoaDonChiTietHolder.tvmahdct.setText("Mã hóa đơn chi tiết: "+listThongKe.get(position).getMahoadonchitiet());
        return convertView;
    }

    public class XemHoaDonChiTietHolder {
        TextView tvmahdct, tvmasach, tvsoluong, tvgiabia;
    }
}
