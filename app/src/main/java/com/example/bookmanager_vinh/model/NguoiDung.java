package com.example.bookmanager_vinh.model;

import java.io.Serializable;

public class NguoiDung implements Serializable{
    private String username;
    private String pass;
    private String phone;
    private String fullname;

    public NguoiDung(String username, String pass, String phone, String fullname) {
        this.username = username;
        this.pass = pass;
        this.phone = phone;
        this.fullname = fullname;
    }

    public NguoiDung(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public NguoiDung() {
        this.username = "no name";
        this.pass = "123456";
        this.phone = "123456";
        this.fullname = "no name";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
