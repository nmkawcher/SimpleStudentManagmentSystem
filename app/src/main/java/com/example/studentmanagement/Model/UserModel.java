package com.example.studentmanagement.Model;

public class UserModel {
    public String name;
    String email;
    String mobile;
    String password;
    Integer userId;

    public UserModel(String name, String email, String mobile, String password, Integer userId) {
        this.name     = name;
        this.email    = email;
        this.mobile   = mobile;
        this.password = password;
        this.userId   = userId;
    }

    public UserModel(String name, String email, String mobile, String password) {
        this.name     = name;
        this.email    = email;
        this.mobile   = mobile;
        this.password = password;
    }
    public UserModel(String name, String email, String mobile) {
        this.name     = name;
        this.email    = email;
        this.mobile   = mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
