package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.adapter.LvThemHoaDonAdapter;
import com.example.bookmanager_vinh.adapter.SpThemHoaDonAdapter;
import com.example.bookmanager_vinh.dao.SachDAO;
import com.example.bookmanager_vinh.model.HoaDon;
import com.example.bookmanager_vinh.model.HoaDonChiTiet;
import com.example.bookmanager_vinh.model.Sach;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThemHoaDonActivity extends AppCompatActivity {
    Spinner spinner;
    SachDAO sachDAO;
    List<Sach> listSachSP;
    EditText edMaHD, edNgayMua, edSoLuong;
    ListView lvThemHoaDon;
    List<HoaDonChiTiet> listHoaDonChiTietDraft;
    Button btnThemVaoGioHang, btnXemHoaDon;
    LvThemHoaDonAdapter lvThemHoaDonAdapter;
    Intent intentXemHoaDon;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        init();
        iconBack();
        listSachSP = new ArrayList<>();
        listSachSP = sachDAO.getAllSach();
        final SpThemHoaDonAdapter spThemHoaDonAdapter = new SpThemHoaDonAdapter(this, listSachSP);


        spinner.setAdapter(spThemHoaDonAdapter);
//       get day month year

        lvThemHoaDonAdapter = new LvThemHoaDonAdapter(this, listHoaDonChiTietDraft);
        lvThemHoaDon.setAdapter(lvThemHoaDonAdapter);
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Sach sach = (Sach) spinner.getSelectedItem();
                if (listHoaDonChiTietDraft.size() > 0) {
                    int index = -1;
                    for (int i = 0; i < listHoaDonChiTietDraft.size(); i++) {
                        if (sach.getMasach().equals(listHoaDonChiTietDraft.get(i).getSach().getMasach())) {
                            index = i;
                            break;
                        }
                    }
                    if (index >= 0) {
                        int tongSoLuongSach = listHoaDonChiTietDraft.get(index).getSach().getSoluong();
                        if ((listHoaDonChiTietDraft.get(index).getSoLuongMua() + Integer.parseInt(edSoLuong.getText().toString())) > tongSoLuongSach) {
                            Toast.makeText(ThemHoaDonActivity.this, "Số lượng sách tối đa mà bạn có thể mua là " + (tongSoLuongSach - listHoaDonChiTietDraft.get(index).getSoLuongMua()) + "", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            HoaDonChiTiet hoaDonChiTiet = listHoaDonChiTietDraft.get(index);
                            int soLuong = hoaDonChiTiet.getSoLuongMua() + Integer.parseInt(edSoLuong.getText().toString());
                            HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(hoaDonChiTiet.getHoaDon(),
                                    sach, soLuong);
                            listHoaDonChiTietDraft.set(index, hoaDonChiTiet1);
                            lvThemHoaDonAdapter.notifyDataSetChanged();
                        }


                    } else {
                        themVaoGioHang();
                    }
                } else {
                    themVaoGioHang();

                }


            }
        });

        btnXemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentXemHoaDon = new Intent(ThemHoaDonActivity.this, XemHoaDonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mahoadon", edMaHD.getText().toString());
                bundle.putString("ngaymua", edNgayMua.getText().toString());
                List<Sach> sachHoaDon = new ArrayList<>();
                ArrayList<Integer> soLuong = new ArrayList<>();
                for (int i = 0; i < listHoaDonChiTietDraft.size(); i++) {
                    Sach sach = listHoaDonChiTietDraft.get(i).getSach();
                    sachHoaDon.add(sach);
                    soLuong.add(listHoaDonChiTietDraft.get(i).getSoLuongMua());
                }
                intentXemHoaDon.putIntegerArrayListExtra("soLuong", soLuong);
                intentXemHoaDon.putExtra("list", (Serializable) sachHoaDon);
                intentXemHoaDon.putExtra("Bundle", bundle);
                startActivity(intentXemHoaDon);

            }
        });


    }

    private void themVaoGioHang() {
        final Sach sach = (Sach) spinner.getSelectedItem();
        String maHoaDon = edMaHD.getText().toString();
        edMaHD.setEnabled(false);
        edNgayMua.setEnabled(false);
        String soLuong = edSoLuong.getText().toString();
        if (Integer.parseInt(soLuong) > sach.getSoluong()) {
            Toast.makeText(ThemHoaDonActivity.this, "Số lượng sách còn lại trong kho là :" + sach.getSoluong(), Toast.LENGTH_SHORT).show();
            return;
        }
        HoaDon hoaDon = new HoaDon(maHoaDon, Calendar.getInstance().getTime());
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(hoaDon, sach, Integer.parseInt(soLuong));
        listHoaDonChiTietDraft.add(hoaDonChiTiet1);
        lvThemHoaDonAdapter.notifyDataSetChanged();
    }

    private void init() {
        spinner = findViewById(R.id.spTenSach);
        sachDAO = new SachDAO(this);
        edMaHD = findViewById(R.id.edMaHDXHD);
        edNgayMua = findViewById(R.id.edNgayMuaXHD);
        edSoLuong = findViewById(R.id.edSoLuong);
        lvThemHoaDon = findViewById(R.id.lvThemHoaDon);
        listHoaDonChiTietDraft = new ArrayList<>();
        btnThemVaoGioHang = findViewById(R.id.btnThemGioHang);
        btnXemHoaDon = findViewById(R.id.btnXemHoaDon);
    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        edNgayMua.setText(year + "-" + month + "-" + day);
        edNgayMua.setEnabled(false);

    }
}
