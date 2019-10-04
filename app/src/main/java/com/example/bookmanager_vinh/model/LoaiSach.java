package com.example.bookmanager_vinh.model;

public class LoaiSach {
    private String maTheLoai;
    private String tenTheLoai;
    private String moTa;

    public LoaiSach(String maTheLoai, String tenTheLoai, String moTa) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
    }

    public LoaiSach() {
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
