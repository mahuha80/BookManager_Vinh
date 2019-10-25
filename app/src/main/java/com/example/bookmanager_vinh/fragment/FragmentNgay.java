package com.example.bookmanager_vinh.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class FragmentNgay extends Fragment {
    PieChart pieChart;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    double doanhthutheongay=0;
    int[] color = {Color.GRAY, Color.CYAN, Color.BLUE, Color.GREEN, Color.WHITE, Color.TRANSPARENT};

    @Override
    public void onResume() {
        super.onResume();
        doanhthutheongay=hoaDonChiTietDAO.getDoanhThuTheoNgay();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hoaDonChiTietDAO=new HoaDonChiTietDAO(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngay, container, false);
        pieChart=view.findViewById(R.id.pcNgay);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doanhthutheongay=hoaDonChiTietDAO.getDoanhThuTheoNgay();
        Toast.makeText(getActivity().getApplicationContext(), "Doanh thu theo ngay la :"+doanhthutheongay, Toast.LENGTH_SHORT).show();
        PieDataSet pieDataSet=new PieDataSet(dataValue1(),"");
        pieDataSet.setColors(color);
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setCenterText("Doanh thu theo ngày là:" +doanhthutheongay);
        pieChart.setCenterTextColor(getResources().getColor(R.color.colorPrimaryDark,null));
        pieChart.invalidate();
    }

    private ArrayList<PieEntry> dataValue1() {
        ArrayList<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(5,"Monday"));
        list.add(new PieEntry(95,"Tuesday"));

        return list;
    }
}
