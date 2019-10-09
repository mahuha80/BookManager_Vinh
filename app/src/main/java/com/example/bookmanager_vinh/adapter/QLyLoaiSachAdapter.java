package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.LoaiSach;

import java.util.List;

public class QLyLoaiSachAdapter extends BaseAdapter {
    List<LoaiSach> listLoaiSach;
    private Context context;

    public QLyLoaiSachAdapter(Context context,List<LoaiSach> listLoaiSach) {
        this.listLoaiSach = listLoaiSach;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listLoaiSach.size();
    }

    @Override
    public LoaiSach getItem(int i) {
        return listLoaiSach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        QLyLoaiSachAdapter.LoaiSachHolder loaiSachHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_quanlysach, viewGroup, false);
            loaiSachHolder=new QLyLoaiSachAdapter.LoaiSachHolder();
            loaiSachHolder.tvTenSach=view.findViewById(R.id.tvSTT);
            loaiSachHolder.tvSoLuong=view.findViewById(R.id.tvNgayThangNam);
            view.setTag(loaiSachHolder);
        }else{
            loaiSachHolder= (QLyLoaiSachAdapter.LoaiSachHolder) view.getTag();
        }
        loaiSachHolder.tvTenSach.setText(listLoaiSach.get(i).getTenTheLoai());
        loaiSachHolder.tvSoLuong.setText(listLoaiSach.get(i).getVitri()+"");
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void dataSetChange(List<LoaiSach> loaiSach){
        this.listLoaiSach=loaiSach;
        notifyDataSetChanged();
    }

    private class LoaiSachHolder {
        TextView tvTenSach,tvSoLuong;
        ImageView imgDel;
    }
}


