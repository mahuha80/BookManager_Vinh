package com.example.bookmanager_vinh.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.model.ThongKe;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EasingOption.EaseInOutQuad;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FragmentNgay extends Fragment {
    PieChart pieChart;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    double doanhthutheongay = 0;
    private Context context;
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
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#00BCD4"),
            Color.parseColor("#009688")




    };
    List<ThongKe> listThongKe;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        doanhthutheongay = hoaDonChiTietDAO.getDoanhThuTheoNgay();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngay, container, false);
        pieChart = view.findViewById(R.id.pcNgay);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listThongKe = new ArrayList<>();
        try {
            listThongKe = hoaDonChiTietDAO.getTungMaDoanhThuTheoNgay();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doanhthutheongay = hoaDonChiTietDAO.getDoanhThuTheoNgay();
        PieDataSet pieDataSet = new PieDataSet(dataValue1(), "MHD-MHDCT");
        pieDataSet.setColors(color);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.parseColor("#FFFFFF"));
        pieChart.setData(pieData);
        pieChart.setCenterText("Doanh thu theo ngày là:" + doanhthutheongay);
        pieChart.setCenterTextColor(getResources().getColor(R.color.colorPrimaryDark, null));
        pieChart.invalidate();
        pieChart.animateY(1000);
        pieChart.setDrawSliceText(false); // To remove slice text
        pieChart.setDrawMarkers(false); // To remove markers when click
        pieChart.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart.getDescription().setEnabled(false); // To remove description of pie
        Legend l = pieChart.getLegend(); // get legend of pie
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // set vertical alignment for legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // set horizontal alignment for legend
        l.setOrientation(Legend.LegendOrientation.VERTICAL); // set orientation for legend
        l.setDrawInside(false); // set if legend should be drawn inside or not
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                int index=
//                Toast.makeText(context, , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private ArrayList<PieEntry> dataValue1() {
        ArrayList<PieEntry> list = new ArrayList<>();
        for (int i = 0; i < listThongKe.size(); i++) {
            float percent = (float) (Double.parseDouble(listThongKe.get(i).getTongtien()) ) ;
            list.add(new PieEntry(percent, listThongKe.get(i).getMahoadon()+"-"+listThongKe.get(i).getMahoadonchitiet()));
        }


        return list;
    }


}
