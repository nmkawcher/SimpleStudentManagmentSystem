package com.example.studentmanagement.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.Model.UserModel;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.ShaaredPrefereanceManager;

public class ProfileFragment extends Fragment {

    private TextView profileNameBig,profileNameSmall,profileNameEmail,profileNameMobile;
    private DBHelper dbHelper;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_profile,container,false);
        init(view);
        loadData();
        return view;
    }

    private void loadData() {
        ShaaredPrefereanceManager spm=new ShaaredPrefereanceManager(getContext());
        String email=spm.getLoggedEmail();
        UserModel userModel=dbHelper.getSingleUserInfo(email);
        profileNameBig.setText(userModel.getName());
        profileNameSmall.setText(userModel.getName());
        profileNameEmail.setText(userModel.getEmail());
        profileNameMobile.setText(userModel.getMobile());
    }

    private void init(View view) {
        profileNameBig =view.findViewById(R.id.profileNameBig);
        profileNameSmall =view.findViewById(R.id.profileNameSmall);
        profileNameEmail =view.findViewById(R.id.profileNameEmail);
        profileNameMobile =view.findViewById(R.id.profileNameMobile);
        dbHelper=new DBHelper(getContext());
    }
}