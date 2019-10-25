package com.example.bookmanager_vinh.model;

public class ThongKe {
    private String mahoadon;
    private String mahoadonchitiet;
    private String ngaymua;
    private String tongtien;
    private String masach;

    public ThongKe(String mahoadon, String mahoadonchitiet, String ngaymua, String tongtien, String masach) {
        this.mahoadon = mahoadon;
        this.mahoadonchitiet = mahoadonchitiet;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
        this.masach = masach;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(String mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }
}
