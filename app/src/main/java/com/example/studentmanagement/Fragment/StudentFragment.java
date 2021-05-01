package com.example.studentmanagement.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentmanagement.Adapter.StudentAdapter;
import com.example.studentmanagement.Model.StudentModel;
import com.example.studentmanagement.R;
import com.example.studentmanagement.Util.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class StudentFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<StudentModel>studentList;
    private DBHelper dbHelper;
    private StudentAdapter studentAdapter;


    public StudentFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_student,container,false);

        init(view);
        loadData();
        onclick();



        return view;
    }

    private void onclick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });
    }

    private EditText etName,etEmail, etMobile,etUsername,etRoll,etCpassword;
    private AppCompatButton btnRegistration;
    private Spinner spinner;
    private Dialog dialog;
    private void addDialog() {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.user_registratrion_layout);
        dialog.show();
        etName        = dialog.findViewById(R.id.etName);
        etEmail       = dialog.findViewById(R.id.etEmail);
        etMobile      = dialog.findViewById(R.id.etMobile);
        etMobile.setVisibility(View.GONE);
        spinner=dialog.findViewById(R.id.userType);
        spinner.setVisibility(View.GONE);
        etRoll   = dialog.findViewById(R.id.etPassword);
        etRoll.setHint("Roll");
        btnRegistration = dialog.findViewById(R.id.btnRegistration);


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=etName.getText().toString();
                String email=etEmail.getText().toString();
                String roll=etRoll.getText().toString();

                if(etName.getText().equals("")){
                    etName.setError("Required");
                    etName.requestFocus();
                    return;
                } if(etName.getText().equals("")){
                    etEmail.setError("Required");
                   etEmail.requestFocus();
                    return;
                }if(etRoll.getText().equals("")){
                    etRoll.setError("Required");
                   etRoll.requestFocus();
                    return;
                }

                insertDataToDatabase(name,email,roll);
            }
        });
    }

    private void insertDataToDatabase(String name, String email, String roll) {

        int roll1=Integer.parseInt(roll);

        StudentModel studentModel=new StudentModel(name,email,roll1);
      long id= dbHelper.insertStudent(studentModel);
      if(id>0){
          Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
          loadData();
          dialog.dismiss();

      }else {
          Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
      }


    }

    //load data from database and set to recyclerview
    private void loadData() {

        studentList=dbHelper.getAllStudentInfo();
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        studentAdapter=new StudentAdapter(studentList,getContext());
        recyclerView.setAdapter(studentAdapter);

    }

    private void init(View view) {

        recyclerView=view.findViewById(R.id.recyclerView);
        fab=view.findViewById(R.id.fab);
        dbHelper=new DBHelper(getContext());


    }
}