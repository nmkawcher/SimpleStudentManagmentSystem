package com.example.studentmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Model.UserModel;

public class RegistrationActivity extends AppCompatActivity {
    private TextView tvResult;
    private EditText etName,etEmail,etMobile,etUsername,etPassword,etCpassword;
    private AppCompatButton btnRegistration;
    private UserModel userModel;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etName        = findViewById(R.id.etName);
        etEmail       = findViewById(R.id.etEmail);
        etMobile      = findViewById(R.id.etMobile);
        etPassword    = findViewById(R.id.etPassword);
        etCpassword   = findViewById(R.id.etCpassword);
        btnRegistration = findViewById(R.id.btnRegistration);


        dbHelper = new DBHelper(getApplicationContext());

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name            = etName.getText().toString().trim();
                String email           = etEmail.getText().toString().trim();
                String mobile          = etMobile.getText().toString().trim();
                String password        = etPassword.getText().toString().trim();
                String confirmPassword = etCpassword.getText().toString().trim();
                if(name.equals("")){
                    etName.setError("Required");
                    etName.requestFocus();
                    return;
                }
                if(email.equals("")){
                    etEmail.setError("Required");
                    etEmail.requestFocus();
                    return;
                }
                if(mobile.equals("")){
                    etMobile.setError("Required");
                    etName.requestFocus();
                    return;
                }if(name.equals("")){
                    etName.setError("Required");
                    etName.requestFocus();
                    return;
                }
                if(password.equals("")){
                    etPassword.setError("Required");
                    etPassword.requestFocus();
                    return;
                }
                if(confirmPassword.equals("")){
                    etCpassword.setError("Required");
                    etCpassword.requestFocus();
                    return;
                }
                if(!password.equals(confirmPassword)){
                    etPassword.setError("Password and confirm password mitch match");
                    etPassword.requestFocus();
                    return;
                }else{
                    //duplicate email check
                   int ckEmail =  dbHelper.checkEmail(email);
                   if(ckEmail ==0){
                       userModel = new UserModel(name,email,mobile,password);
                       long id = dbHelper.insertUser(userModel);
                       if(id >-1){
                           Toast.makeText(RegistrationActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                           startActivity(intent);
                           finish();
                       }else{
                           Toast.makeText(RegistrationActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(RegistrationActivity.this, "This email already used!!", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }
}