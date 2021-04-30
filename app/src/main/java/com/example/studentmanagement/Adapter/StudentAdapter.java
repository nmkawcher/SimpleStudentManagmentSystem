package com.example.studentmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagement.Model.StudentModel;
import com.example.studentmanagement.Model.UserModel;
import com.example.studentmanagement.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<StudentModel>studentList;
    private Context context;

    public StudentAdapter(ArrayList<StudentModel> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.student_rv_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentModel model=studentList.get(position);
        holder.nameTV.setText(model.getName());
        holder.rollTV.setText(model.getRoll());
        holder.emailTV.setText(model.getEmail());



    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTV,emailTV,rollTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.student_name);
           emailTV=itemView.findViewById(R.id.email);
            rollTV=itemView.findViewById(R.id.roll);
        }
    }
}
