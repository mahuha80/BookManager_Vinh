package com.example.bookmanager_vinh.fragment;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentNode extends FragmentPagerAdapter {
    public FragmentNode(FragmentManager fm) {
        super(fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                return new FragmentThongKeDoanhThuTheoNgay();
            case 1:
                return new FragmentThongKeDoanhThuTGCT();
            case 2:
                return new FragmentThongKeDoanhThuTheoNam();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                return "Thống kê theo ngày";
            case 1:
                return "Thống kê theo khoảng thời gian";
            case 2:
                return "Thống kê theo năm";

        }
        return null;

    }
}
