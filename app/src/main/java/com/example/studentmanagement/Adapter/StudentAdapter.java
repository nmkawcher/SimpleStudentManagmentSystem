package com.example.studentmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagement.Model.StudentModel;
import com.example.studentmanagement.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private ArrayList<StudentModel>studentList;
    private Context context;

    public StudentAdapter(ArrayList<StudentModel> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.student_rv_item,parent,false);

        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {

        StudentModel model=studentList.get(position);
        holder.nameTV.setText(model.getName());
        holder.rollTV.setText(String.valueOf(model.getRoll()));
        holder.emailTV.setText(model.getEmail());



    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class StudentHolder extends RecyclerView.ViewHolder{

        private TextView nameTV,emailTV,rollTV;
        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.student_name);
           emailTV=itemView.findViewById(R.id.email);
            rollTV=itemView.findViewById(R.id.roll);
        }
    }
}
