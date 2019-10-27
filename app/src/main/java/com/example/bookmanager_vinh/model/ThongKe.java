package com.example.bookmanager_vinh.model;

public class ThongKe {
    private String mahoadon;
    private String mahoadonchitiet;
    private String ngaymua;
    private String tongtien;
    private String masach;
    private String soluong;
    private String giabia;


    public ThongKe( String mahoadonchitiet, String masach, String soluong, String giabia) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.masach = masach;
        this.soluong = soluong;
        this.giabia = giabia;
    }

    public ThongKe(String mahoadon, String ngaymua, String tongtien) {
        this.mahoadon = mahoadon;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
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
