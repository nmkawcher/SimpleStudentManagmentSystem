package com.example.studentmanagement.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanagement.Activity.LoginActivity;
import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.Model.UserModel;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.ShaaredPrefereanceManager;

public class PasswordFragment extends Fragment {
    private TextView profileNameBig;
    private DBHelper dbHelper;
    private UserModel userModel;
    private AppCompatButton btnChangePassword;
    private EditText etNewPassword,etConfirmNewPassword,etCurrentPassword;
    private ShaaredPrefereanceManager spm;
    public PasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_password,container,false);
        init(view);
        loadData();
        return view;
    }
    private void loadData() {
        ShaaredPrefereanceManager spm = new ShaaredPrefereanceManager(getContext());
        String myEmail = spm.getLoggedEmail();
        userModel = dbHelper.getSingleUserInfo(myEmail);
        profileNameBig.setText(userModel.getName());
    }

    private void init(View view) {
        profileNameBig = view.findViewById(R.id.profileNameBig);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        etNewPassword = view.findViewById(R.id.etNewPassword);
        etConfirmNewPassword = view.findViewById(R.id.etConfirmNewPassword);
        etCurrentPassword = view.findViewById(R.id.etCurrentPassword);
        dbHelper = new DBHelper(getContext());
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShaaredPrefereanceManager spm =ShaaredPrefereanceManager.getInstance(getContext());
                String loginEmail = spm.getLoggedEmail();
                UserModel userModel = dbHelper.getSingleUserInfo(loginEmail);
                //current password
                String currentStorePass = userModel.getPassword();

                String newPass = etNewPassword.getText().toString().trim();
                String confirmPass = etConfirmNewPassword.getText().toString().trim();
                String currentPassword = etCurrentPassword.getText().toString().trim();
                if(currentPassword.equals("")){
                    etCurrentPassword.setError("Required");
                    etCurrentPassword.requestFocus();
                    return;
                }
                if(!currentPassword.equals(currentStorePass)){
                    etCurrentPassword.setError("Current Password Not match");
                    etCurrentPassword.requestFocus();
                    return;
                }
                if(newPass.equals("")){
                    etNewPassword.setError("Required");
                    etNewPassword.requestFocus();
                    return;
                }
                if(confirmPass.equals("")){
                    etConfirmNewPassword.setError("Required");
                    etConfirmNewPassword.requestFocus();
                    return;
                }
                if(!confirmPass.equals(newPass)){
                    etConfirmNewPassword.setError("New Password and Confirm Password Not match");
                    etConfirmNewPassword.requestFocus();
                    return;
                }else{
                    //update password here
                    long updatePassword = dbHelper.updateUser(loginEmail,newPass);
                    if(updatePassword >-1){
                        ShaaredPrefereanceManager spm2 = new ShaaredPrefereanceManager(getContext());
                        spm2.setLoginStatus("false");
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        Toast.makeText(getContext(), "Password successfully update", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}