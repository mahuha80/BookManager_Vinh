package com.example.bookmanager_vinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.List;

public class DanhSachNguoiDungAdapter extends BaseAdapter {
    private Context context;
    private List<NguoiDung> listNguoiDung;

    public DanhSachNguoiDungAdapter(Context context, List<NguoiDung> listNguoiDung) {
        this.context = context;
        this.listNguoiDung = listNguoiDung;
    }

    @Override
    public int getCount() {
        return listNguoiDung.size();
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
        nguoiDungHolder.tvSoDienThoai.setText(listNguoiDung.get(i).getPhone());
        nguoiDungHolder.tvTen.setText(listNguoiDung.get(i).getUsername());
        if(listNguoiDung.size()>8){
            Animation animation=AnimationUtils.loadAnimation(context,R.anim.animation_listview);
            view.setAnimation(animation);
            view.startAnimation(animation);
        }

        return view;
    }
    public class NguoiDungHolder{
        TextView tvTen,tvSoDienThoai;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<NguoiDung> nguoiDungList){
        this.listNguoiDung=nguoiDungList;
        notifyDataSetChanged();
    }
}
