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

public class LvThongKeHoaDonFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<ThongKe> listThongKe;

    public LvThongKeHoaDonFragmentAdapter(Context context, List<ThongKe> listThongKe) {
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
        ThongKeHolder thongKeHolder=null;
        if(convertView==null){
            thongKeHolder=new ThongKeHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.lv_thongkehoadonfragment,parent,false);
            thongKeHolder.tvMHD=convertView.findViewById(R.id.tvMHD);
            thongKeHolder.tvTongTien=convertView.findViewById(R.id.tvTongTienMHD);
            thongKeHolder.tvTime=convertView.findViewById(R.id.tvTimeMHD);
            convertView.setTag(thongKeHolder);
        }else{
            thongKeHolder= (ThongKeHolder) convertView.getTag();
        }
        thongKeHolder.tvTime.setText(listThongKe.get(position).getNgaymua());
        thongKeHolder.tvTongTien.setText(listThongKe.get(position).getTongtien());
        thongKeHolder.tvMHD.setText(listThongKe.get(position).getMahoadon());
        return convertView;
    }
    public class ThongKeHolder{
        TextView tvMHD,tvTongTien,tvTime;
    }
}
