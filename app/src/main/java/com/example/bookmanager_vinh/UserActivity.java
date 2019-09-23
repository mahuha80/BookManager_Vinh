package com.example.bookmanager_vinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.add_option:
                startActivity(new Intent(UserActivity.this, QLyNguoiDungActivity.class));
                break;
            case R.id.changePass_option:
                break;
            case R.id.logOut_option:
                startActivity(new Intent(UserActivity.this,LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
