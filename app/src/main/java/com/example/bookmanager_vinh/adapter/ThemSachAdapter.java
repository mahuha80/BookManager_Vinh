package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.Sach;

import java.util.List;

public class ThemSachAdapter implements SpinnerAdapter {
    private Context context;
    private List<Sach> listSach;

    public ThemSachAdapter(Context context, List<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.sp_themsach,viewGroup,false);
        TextView textView=view.findViewById(R.id.tvItem);
        textView.setText(listSach.get(i).getSoLuong()+" | "+listSach.get(i).getTenSach());
        textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        return view;
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
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.sp_themsach,viewGroup,false);
        TextView textView=view.findViewById(R.id.tvItem);
        textView.setText(listSach.get(i).getSoLuong()+" | "+listSach.get(i).getTenSach());
        return view;
    }

    @Override
    public int getItemViewType(int i) {
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
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }
}
