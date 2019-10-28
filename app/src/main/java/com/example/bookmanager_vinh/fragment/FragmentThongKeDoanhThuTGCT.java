package com.example.bookmanager_vinh.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvThongKeHoaDonAdapter;
import com.example.bookmanager_vinh.dao.HoaDonChiTietDAO;
import com.example.bookmanager_vinh.model.ThongKe;
import com.example.bookmanager_vinh.ui.XemHoaDonChiTiet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentThongKeDoanhThuTGCT extends Fragment {
    private Context context;
    ImageView imgFrom, imgTo;
    EditText edFrom, edTo;
    Button btnTimTKDT;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    List<ThongKe> listThongKe;
    ListView lvThongKeHoaDonTheoKhoangThoiGian;
    TextView tvTongDoanhThuTheoKhoangThoiGian;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thang, container, false);
        imgFrom = view.findViewById(R.id.imgCalendarFrom);
        imgTo = view.findViewById(R.id.imgCalendarTo);
        edFrom = view.findViewById(R.id.edDateFrom);
        edTo = view.findViewById(R.id.edDateTo);
        btnTimTKDT = view.findViewById(R.id.btnTimTKDT);
        lvThongKeHoaDonTheoKhoangThoiGian = view.findViewById(R.id.lvTimHoaDonTheoKhoangThoiGian);
        tvTongDoanhThuTheoKhoangThoiGian = view.findViewById(R.id.tvTongDoanhThuTheoKhoangThoiGian);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listThongKe = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
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
                edFrom.setText(year + "-" + monthS + "-" + dayS);
            }
        }, year, month, day);
        imgFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        final DatePickerDialog datePickerDialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                edTo.setText(year + "-" + monthS + "-" + dayS);

            }
        }, year, month, day);
        imgTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog1.show();
            }
        });
        btnTimTKDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edFrom.getText().toString().equals("") || edTo.getText().toString().equals("")) {
                    Toast.makeText(context, "Vui lòng chọn ngày ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    listThongKe = hoaDonChiTietDAO.getHoaDonTheoKhoangThoiGian(edFrom.getText().toString(), edTo.getText().toString());
                    if (listThongKe.size() > 0) {
                        double result = getTongDoanhThuTheoKhoangThoiGian(listThongKe);
                        tvTongDoanhThuTheoKhoangThoiGian.setText("Tổng doanh thu từ "+edFrom.getText().toString()+" đến "+edTo.getText().toString()+" là :\n"+result + "");
                        LvThongKeHoaDonAdapter lvThongKeHoaDonFragmentAdapter = new LvThongKeHoaDonAdapter(context, listThongKe);
                        lvThongKeHoaDonTheoKhoangThoiGian.setAdapter(lvThongKeHoaDonFragmentAdapter);
                        lvThongKeHoaDonTheoKhoangThoiGian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), XemHoaDonChiTiet.class);
                                intent.putExtra("mahoadon", listThongKe.get(position).getMahoadon());
                                startActivity(intent);
                            }
                        });
                    } else {
                        listThongKe.clear();
                        LvThongKeHoaDonAdapter lvThongKeHoaDonFragmentAdapter = new LvThongKeHoaDonAdapter(context, listThongKe);
                        lvThongKeHoaDonTheoKhoangThoiGian.setAdapter(lvThongKeHoaDonFragmentAdapter);
                    }

                }
            }
        });
    }

    private double getTongDoanhThuTheoKhoangThoiGian(List<ThongKe> list) {
        double tongdoanhthu = 0;
        for (int i = 0; i < list.size(); i++) {
            double tong = Double.parseDouble(list.get(i).getTongtien());
            tongdoanhthu += tong;
        }
        return tongdoanhthu;
    }
}
