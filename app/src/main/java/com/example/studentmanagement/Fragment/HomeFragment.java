package com.example.studentmanagement.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentmanagement.R;

public class HomeFragment extends Fragment {
    private CardView teacherCV,dashBoardCV,studentCV,courseCV;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);

        init(view);
        clickListener();
        return view;
    }

    private void clickListener() {

        studentCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new StudentFragment());
            }
        });

        dashBoardCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new DashboardFragment());
            }
        });

        courseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new CourseFragment());
            }
        });

        teacherCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragment(new TeacherFragment());
            }
        });
    }

    private void init(View view) {

        studentCV=view.findViewById(R.id.cv_student);
        dashBoardCV=view.findViewById(R.id.cv_dashboard);
        courseCV=view.findViewById(R.id.cv_course);
       teacherCV=view.findViewById(R.id.cv_teacher);

    }

    private void setFragment(Fragment fragment){

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();


    }
}