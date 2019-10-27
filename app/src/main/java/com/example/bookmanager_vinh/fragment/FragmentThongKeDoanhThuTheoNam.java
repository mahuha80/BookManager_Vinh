package com.example.bookmanager_vinh.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class FragmentThongKeDoanhThuTheoNam extends Fragment {
    Context context;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    BarChart barChart;
    TextView tvTe;
    TextView tvDoanhThuNam;
    int[] color = new int[]{
            Color.parseColor("#F44336"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#4CAF50"),
            Color.parseColor("#8BC34A"),
            Color.parseColor("#CDDC39"),
            Color.parseColor("#FFEB3B"),
            Color.parseColor("#FFC107"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#00BCD4"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800")


    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nam, container, false);
        barChart = view.findViewById(R.id.BcNam);
        tvDoanhThuNam=view.findViewById(R.id.tvTongDoanhThuNam);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        tvDoanhThuNam.setText("Tổng doanh thu theo năm: "+hoaDonChiTietDAO.getDoanhThuTheoNam()+"");
        BarDataSet barDataSet = new BarDataSet(getAllList(), "DOANH THU TỪNG THÁNG CỦA CỬA HÀNG");
        barDataSet.setColors(color);
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.invalidate();
        barChart.animateY(1000);
        barChart.setDescription(new Description());
        XAxis xAxis=barChart.getXAxis();
        xAxis.setLabelCount(12);


    }

    public List<BarEntry> getAllList() {
        List<BarEntry> list = new ArrayList<>();
        for (int i = 0; i < hoaDonChiTietDAO.getTongDoanhThuMoiThangTrongNam().size(); i++) {
            double tongTien = Double.parseDouble(hoaDonChiTietDAO.getTongDoanhThuMoiThangTrongNam().get(i).getTongdoanhthuthang());
            list.add(new BarEntry(i+1, (float) tongTien));
        }

        return list;
    }
}
