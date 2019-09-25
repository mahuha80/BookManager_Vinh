package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;

public class DanhSachNguoiDungAdapter extends BaseAdapter {
    private Context context;

    public DanhSachNguoiDungAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        NguoiDungHolder nguoiDungHolder=null;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.lv_danhsachnguoidung,viewGroup,false);
            nguoiDungHolder=new NguoiDungHolder();
            nguoiDungHolder.tvTen=view.findViewById(R.id.tvTenNguoiDung);
            nguoiDungHolder.tvSoDienThoai=view.findViewById(R.id.tvSDTNguoiDung);
            view.setTag(nguoiDungHolder);
        }else{
            nguoiDungHolder= (NguoiDungHolder) view.getTag();
        }
        nguoiDungHolder.tvSoDienThoai.setText("0987395971");
        nguoiDungHolder.tvTen.setText("Nguyen Thanh Vinh");

        return view;
    }
    public class NguoiDungHolder{
        TextView tvTen,tvSoDienThoai;
    }
}
