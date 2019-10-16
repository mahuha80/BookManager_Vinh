package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.Sach;

import java.util.List;

public class SpThemHoaDonAdapter implements SpinnerAdapter {
    private Context context;
    List<Sach> listSach;

    public SpThemHoaDonAdapter(Context context, List<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.sp_themhoadon,parent,false);
        TextView textView=convertView.findViewById(R.id.tvSachTHD);
        textView.setText(listSach.get(position).getTensach());
        return convertView;
    }
    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Sach getItem(int position) {
        return listSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.sp_themhoadon,parent,false);
        TextView textView=convertView.findViewById(R.id.tvSachTHD);
        textView.setText(listSach.get(position).getTensach());
        return convertView;    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
}
