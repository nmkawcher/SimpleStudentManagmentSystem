package com.example.studentmanagement.Model;

public class StudentModel {
    private String name,email;
    private int roll;

    public StudentModel(String name, String email, int roll) {
        this.name = name;
        this.email = email;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}
