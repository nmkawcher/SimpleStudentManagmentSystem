package com.example.studentmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.ShaaredPrefereanceManager;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler;
    private int timeout = 2000;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShaaredPrefereanceManager shaaredPrefereanceManager = new ShaaredPrefereanceManager(getApplicationContext());
                String loginStatus = shaaredPrefereanceManager.getUserLoginStatus();
                if(loginStatus.equals("true")){
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },timeout);

    }
}