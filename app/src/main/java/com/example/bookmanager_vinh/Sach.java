package com.example.bookmanager_vinh;

public class Sach {
    private String tenSach;
    private int soLuong;

    public Sach(String tenSach, int soLuong) {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public Sach() {
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
