package com.example.studentmanagement.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentmanagement.Util.DBHelper;
import com.example.studentmanagement.Model.UserModel;
import com.example.studentmanagement.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private CardView cardView;
    private Dialog dialog;
    private TextView tvResult;
    private EditText etName,etEmail,etMobile,etUsername,etPassword,etCpassword;
    private AppCompatButton btnRegistration;
    private UserModel userModel;
    private DBHelper dbHelper;
    public DashboardFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard,container,false);
        init(view);
        myOnClick();
        return view;
    }

    private void myOnClick() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDlalog();
            }
        });
    }

    private void addDlalog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.user_registratrion_layout);
        dialog.show();
        etName        = dialog.findViewById(R.id.etName);
        etEmail       = dialog.findViewById(R.id.etEmail);
        etMobile      = dialog.findViewById(R.id.etMobile);
        etPassword    = dialog.findViewById(R.id.etPassword);
        btnRegistration = dialog.findViewById(R.id.btnRegistration);
        dbHelper = new DBHelper(getContext());
        initSpainerData();
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initSpainerData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("select user_type");
        arrayList.add("student");
        arrayList.add("teacher");
        arrayList.add("admin");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void init(View view) {
        cardView = view.findViewById(R.id.userCV);


    }
}