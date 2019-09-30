package com.example.bookmanager_vinh.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String filename = "UserLogin";
    Button btnLogin;
    EditText edUser, edPass;
    CheckBox chkRemember;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin(nguoiDungDAO.isLogin(new NguoiDung(edUser.getText().toString(),edPass.getText().toString())));
            }
        });
    }

    private void isLogin(boolean flag) {
        if (flag) {
            SharedPreferences sharedPreferences = getSharedPreferences(filename, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user", edUser.getText().toString());
            editor.putString("pass", edPass.getText().toString());
            editor.putBoolean("chk", chkRemember.isChecked());
            editor.commit();
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

            return;
        }
    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        edPass = findViewById(R.id.edPass);
        edUser = findViewById(R.id.edUser);
        chkRemember = findViewById(R.id.chkRemember);
        nguoiDungDAO = new NguoiDungDAO(LoginActivity.this);
        listNguoiDung = new ArrayList<>();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = getSharedPreferences(filename, MODE_PRIVATE);
        Boolean chk = sharedPreferences.getBoolean("chk", false);
        if (chk) {
            String user = sharedPreferences.getString("user", "");
            String pass = sharedPreferences.getString("pass", "");
            edUser.setText(user);
            edPass.setText(pass);
        }

        chkRemember.setChecked(chk);
        super.onResume();
    }
}
