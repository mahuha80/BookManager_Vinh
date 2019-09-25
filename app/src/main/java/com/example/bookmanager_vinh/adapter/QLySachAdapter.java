package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.ui.Sach;

import java.util.List;

public class QLySachAdapter extends BaseAdapter {
    List<Sach> listSach;
    private Context context;

    public QLySachAdapter(Context context, List<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Sach getItem(int i) {
        return listSach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SachHolder sachHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_quanlysach, viewGroup, false);
            sachHolder=new SachHolder();
            sachHolder.tvTenSach=view.findViewById(R.id.tvSTT);
            sachHolder.tvSoLuong=view.findViewById(R.id.tvNgayThangNam);
            sachHolder.imgDel=view.findViewById(R.id.imgXoa);
            view.setTag(sachHolder);
        }else{
            sachHolder= (SachHolder) view.getTag();
        }
        sachHolder.tvTenSach.setText(listSach.get(i).getTenSach());
        sachHolder.tvSoLuong.setText(listSach.get(i).getSoLuong()+"");
        return view;
    }

    private class SachHolder {
        TextView tvTenSach,tvSoLuong;
        ImageView imgDel;
    }
}
