package com.example.bookmanager_vinh.model;

import java.io.Serializable;

public class HoaDonChiTiet implements Serializable {
    private int maHDCT;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuongMua;

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, Sach sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(HoaDon hoaDon, Sach sach, int soLuongMua) {
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet() {
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
