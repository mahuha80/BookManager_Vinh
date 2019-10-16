package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.SachDAO;
import com.example.bookmanager_vinh.model.Sach;

import java.util.List;

public class LvQLySachAdapter extends BaseAdapter {
    List<Sach> listSach;
    private Context context;
    SachDAO sachDAO;

    public LvQLySachAdapter(Context context, List<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
        sachDAO=new SachDAO(context);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        SachHolder sachHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_quanlysach, viewGroup, false);
            sachHolder = new SachHolder();
            sachHolder.tvTenSach = view.findViewById(R.id.tvTenSach);
            sachHolder.tvGiaSach= view.findViewById(R.id.tvGiaSach);
            sachHolder.imgXoa=view.findViewById(R.id.imgXoa);
            view.setTag(sachHolder);
        } else {
            sachHolder = (SachHolder) view.getTag();
        }
        sachHolder.tvTenSach.setText(listSach.get(i).getTensach());
        Log.e("DEBUG",listSach.get(i).getTensach()+"");
        sachHolder.tvGiaSach.setText(listSach.get(i).getGiabia()+"");
        sachHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach=listSach.get(i);
                int result = sachDAO.deleteSach(sach.getMasach());
                if (result > 0) {
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    //ok
                    listSach.remove(i);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void ondatasetchanged(List<Sach> listSach) {
        this.listSach = listSach;
        notifyDataSetChanged();

    }

    private class SachHolder {
        TextView tvTenSach, tvGiaSach;
        ImageView imgXoa;
    }
}
