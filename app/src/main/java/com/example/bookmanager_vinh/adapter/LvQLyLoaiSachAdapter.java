package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.dao.TheLoaiDAO;
import com.example.bookmanager_vinh.model.LoaiSach;

import java.util.List;

public class LvQLyLoaiSachAdapter extends BaseAdapter {
    List<LoaiSach> listLoaiSach;
    TheLoaiDAO theLoaiDAO;
    NguoiDungDAO nguoiDungDAO;
    private Context context;

    public LvQLyLoaiSachAdapter(Context context, List<LoaiSach> listLoaiSach) {
        this.listLoaiSach = listLoaiSach;
        this.context = context;
        theLoaiDAO = new TheLoaiDAO(context);
        nguoiDungDAO = new NguoiDungDAO(context);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LvQLyLoaiSachAdapter.LoaiSachHolder loaiSachHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_quanlytheloaisach, viewGroup, false);
            loaiSachHolder = new LvQLyLoaiSachAdapter.LoaiSachHolder();
            loaiSachHolder.tvMaTheLoai = view.findViewById(R.id.tvMaTheLoai);
            loaiSachHolder.tvTenTheLoai = view.findViewById(R.id.tvTenTheLoai);
            loaiSachHolder.imgDel = view.findViewById(R.id.imgDel);
            view.setTag(loaiSachHolder);
        } else {
            loaiSachHolder = (LvQLyLoaiSachAdapter.LoaiSachHolder) view.getTag();
        }
        loaiSachHolder.tvMaTheLoai.setText(listLoaiSach.get(i).getTenTheLoai());
        loaiSachHolder.tvTenTheLoai.setText(listLoaiSach.get(i).getMaTheLoai() + "");
        loaiSachHolder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = listLoaiSach.get(i);
                int role = nguoiDungDAO.getRoleViaUsername(NguoiDungDAO.usernameLogin);
                if (role == 1) {
                    if (theLoaiDAO.deleteTheLoai(loaiSach.getMaTheLoai()) > 0) {
                        Toast.makeText(context, "Xóa thành công loại sách !", Toast.LENGTH_SHORT).show();
                        listLoaiSach.remove(i);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Xóa không thành công loại sách !", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Bạn không có quyền xóa", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void dataSetChange(List<LoaiSach> loaiSach) {
        this.listLoaiSach = loaiSach;
        notifyDataSetChanged();
    }

    private class LoaiSachHolder {
        TextView tvMaTheLoai, tvTenTheLoai;
        ImageView imgDel;
    }
}


