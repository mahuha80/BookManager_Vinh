package com.example.bookmanager_vinh.model;

import java.io.Serializable;

public class Sach implements Serializable {
    private String masach;
    private String matheloai;
    private String tensach;
    private String tacgia;
    private String nxb;
    private String giabia;
    private String soluong;

    public Sach() {
    }

    public Sach(String masach, String matheloai, String tensach, String tacgia, String nxb, String giabia, String soluong) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
