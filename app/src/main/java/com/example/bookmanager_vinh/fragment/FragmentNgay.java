package com.example.bookmanager_vinh.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvThongKeHoaDonFragmentAdapter;
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
import java.util.Calendar;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EasingOption.EaseInOutQuad;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FragmentNgay extends Fragment {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private Context context;
    EditText edFragmentNgay;
    ImageView imgCalendar;
    Button btnTim;
    ListView lv;
    List<ThongKe> listThongKe;
    LvThongKeHoaDonFragmentAdapter lvThongKeHoaDonFragmentAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();

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
        edFragmentNgay = view.findViewById(R.id.edFragmentNgay);
        imgCalendar = view.findViewById(R.id.imgCalendar);
        btnTim = view.findViewById(R.id.btnFragmentNgay);
        lv = view.findViewById(R.id.lvFragmentNgay);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listThongKe = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int year = calendar.get(Calendar.YEAR);
        edFragmentNgay.setEnabled(false);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edFragmentNgay.setText(year + "-" + month + "-" + dayOfMonth);

            }
        }, year, month, day);
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 0;
                try {
                    listThongKe = hoaDonChiTietDAO.getTungMaDoanhThuTheoNgay(edFragmentNgay.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(context, "sai r", Toast.LENGTH_SHORT).show();
                }

                if (listThongKe.size() > 0) {
                    lvThongKeHoaDonFragmentAdapter = new LvThongKeHoaDonFragmentAdapter(context, listThongKe);
                    lv.setAdapter(lvThongKeHoaDonFragmentAdapter);
                } else {
                    listThongKe.clear();
                    lvThongKeHoaDonFragmentAdapter = new LvThongKeHoaDonFragmentAdapter(context, listThongKe);
                    lv.setAdapter(lvThongKeHoaDonFragmentAdapter);                }
            }
        });


    }
}
