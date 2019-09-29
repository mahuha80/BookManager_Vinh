package com.example.bookmanager_vinh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanager_vinh.R;
import com.example.bookmanager_vinh.dao.NguoiDungDAO;
import com.example.bookmanager_vinh.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edUser,edPass;

    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<listNguoiDung.size();i++){
                    String user=listNguoiDung.get(i).getUsername();
                    String pass=listNguoiDung.get(i).getPass();
                    if(user.equals(edUser.getText().toString())&&pass.equals(edPass.getText().toString())){
                        flag=true;
                        loginSuccess(flag);
                        break;
                    }
                }
            }
        });
    }
    private void loginSuccess(boolean flag){
        if(flag){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            return ;
        }
    }
    private void init() {
        btnLogin=findViewById(R.id.btnLogin);
        edPass=findViewById(R.id.edPass);
        edUser=findViewById(R.id.edUser);
        nguoiDungDAO=new NguoiDungDAO(LoginActivity.this);
        listNguoiDung=new ArrayList<>();
        listNguoiDung=nguoiDungDAO.getAllNguoiDung();
    }
}
