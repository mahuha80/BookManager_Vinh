package com.example.bookmanager_vinh.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvThongKeHoaDonAdapter;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.model.ThongKe;
import com.example.bookmanager_vinh.ui.XemHoaDonChiTiet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FragmentThongKeDoanhThuTheoNgay extends Fragment {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private Context context;
    EditText edFragmentNgay;
    ImageView imgCalendar;
    Button btnTim;
    ListView lv;
    List<ThongKe> listThongKe;
    TextView tvTongDoanhThuTheoNgay;
    LvThongKeHoaDonAdapter lvThongKeHoaDonAdapter;

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
        tvTongDoanhThuTheoNgay = view.findViewById(R.id.tvTongDoanhThuTheoNgay);
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
                String monthS = null;
                String dayS;
                if (month < 10) {
                    monthS = "0" + month;
                } else {
                    monthS = month + "";
                }
                if (dayOfMonth < 10) {
                    dayS = "0" + dayOfMonth;
                } else {
                    dayS = dayOfMonth + "";
                }
                edFragmentNgay.setText(year + "-" + monthS + "-" + dayS);

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
                try {
                    listThongKe = hoaDonChiTietDAO.getHoaDonTheoNgay(edFragmentNgay.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(context, "sai r", Toast.LENGTH_SHORT).show();
                }

                if (listThongKe.size() > 0) {
                    lvThongKeHoaDonAdapter = new LvThongKeHoaDonAdapter(context, listThongKe);
                    lv.setAdapter(lvThongKeHoaDonAdapter);
                    double tongDoanhThu = 0;
                    for (int i = 0; i < listThongKe.size(); i++) {
                        double doanhthu = Double.parseDouble(listThongKe.get(i).getTongtien());
                        tongDoanhThu += doanhthu;
                    }
                    tvTongDoanhThuTheoNgay.setText("Tổng doanh thu theo ngày là :" + tongDoanhThu);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), XemHoaDonChiTiet.class);
                            intent.putExtra("mahoadon", listThongKe.get(position).getMahoadon());
                            startActivity(intent);
                        }
                    });
                } else {
                    tvTongDoanhThuTheoNgay.setText("");
                    listThongKe.clear();
                    lvThongKeHoaDonAdapter = new LvThongKeHoaDonAdapter(context, listThongKe);
                    lv.setAdapter(lvThongKeHoaDonAdapter);
                }
            }
        });


    }
}
