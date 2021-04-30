package com.example.studentmanagement.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.ShaaredPrefereanceManager;


public class LoginActivity extends AppCompatActivity {
    private TextView regPageTv;
    private EditText etEmail,etPassword;
    private Button btnLogin;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences;

    private static final String LOGIN_DATA = "login_data";//Shared prefernces name
    private static final String EMAIL_KEY = "email_key";
    private static final String PASSWORD_KEY = "password_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regPageTv = findViewById(R.id.regPageBtn);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                loginUser(email,password);
            }
        });

        regPageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void loginUser(String email, String password) {
        dbHelper = new DBHelper(getApplicationContext());
        int value = dbHelper.loginUser(email,password);
        if(value >0){
            ShaaredPrefereanceManager shaaredPrefereanceManager = new ShaaredPrefereanceManager(getApplicationContext());
            shaaredPrefereanceManager.setLoginStatus("true");
            shaaredPrefereanceManager.setLoggedEmail(email);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            //intent.putExtra("email",email);
            //intent.putExtra("password",password);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}