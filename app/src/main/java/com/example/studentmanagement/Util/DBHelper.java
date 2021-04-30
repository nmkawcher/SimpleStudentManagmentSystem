package com.example.studentmanagement.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.studentmanagement.Model.StudentModel;
import com.example.studentmanagement.Model.UserModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;
    private Context context;
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_USER_TABLE = "create table tbl_user(userId integer primary key autoincrement,name TEXT,email TEXT,mobile TEXT,password TEXT,user_type TEXT)";
        final String SQL_CREATE_STUDENT_TABLE = "create table tbl_student(studentId integer primary key autoincrement,name TEXT,email TEXT,roll TEXT)";
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;
        db.execSQL("DROP TABLE IF EXISTS tbl_user");
        db.execSQL("DROP TABLE IF EXISTS tbl_student");
        onCreate(db);
    }

    //insert data in student table
    public long insertStudent(StudentModel model){
        db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name",model.getName());
        content.put("email",model.getEmail());
        content.put("roll",model.getRoll());

        long row = db.insert("tbl_student",null,content);
        return row;
    }

    public ArrayList<StudentModel> getAllStudentInfo(){

        db=getReadableDatabase();
        ArrayList<StudentModel>list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from tbl_student",null);
        if(cursor.moveToFirst()){
            do{
                String myName=cursor.getString(cursor.getColumnIndex("name"));
                String myEmail=cursor.getString(cursor.getColumnIndex("email"));
                int roll=cursor.getInt(cursor.getColumnIndex("roll"));

                StudentModel model=new StudentModel(myName,myEmail,roll);

                list.add(model);


            }while (cursor.moveToNext());
        }

        return list;
    }


    //insert user into tbl_user
    public long insertUser(UserModel model){
        db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name",model.getName());
        content.put("email",model.getEmail());
        content.put("mobile",model.getMobile());
        content.put("password",model.getPassword());
       long row = db.insert("tbl_user",null,content);
       return row;
    }

    //get all student
    public UserModel getSingleUserInfo(String email){
        String []selectionArgs=new String[]{email};
        db=getReadableDatabase();
        UserModel userModel=null;
        Cursor cursor=db.rawQuery("select * from tbl_user where email =? ",selectionArgs);
        if(cursor.moveToFirst()){
            do{
                String myName=cursor.getString(cursor.getColumnIndex("name"));
                String myEmail=cursor.getString(cursor.getColumnIndex("email"));
                String myMobile=cursor.getString(cursor.getColumnIndex("mobile"));
                String myPassword=cursor.getString(cursor.getColumnIndex("password"));

                userModel=new UserModel(myName,myEmail,myMobile,myPassword);


            }while (cursor.moveToNext());
        }

        return userModel;
    }


    //login user into tbl_user with email and password
    public int loginUser(String email, String password) {
        String[] selectionArgs = new String[]{email, password};
        db = getReadableDatabase();
        int i = 0;
        Cursor c = db.rawQuery("select * from tbl_user where email =? and password =?", selectionArgs);
        if (c.moveToFirst()) {
            do {

            } while (c.moveToNext());
        }
        i = c.getCount();
        c.close();
        return i;
    }

    //check email into tbl_user
    public int checkEmail(String email) {
        String[] selectionArgs = new String[]{email};
        db = getReadableDatabase();
        int i = 0;
        Cursor c = db.rawQuery("select * from tbl_user where email =? ", selectionArgs);
        if (c.moveToFirst()) {
            do {

            } while (c.moveToNext());
        }
        i = c.getCount();
        c.close();
        return i;
    }

    //check email into tbl_user
    public long updateUser(String email,String password) {
        String[] selectionArgs = new String[]{email};
        db = getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put("password",password);
       long row = db.update("tbl_user",content,"email = ?",selectionArgs);
       return row;
    }
}
