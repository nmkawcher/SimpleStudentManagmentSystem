package com.example.studentmanagement.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private void addDialog() {



    }

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