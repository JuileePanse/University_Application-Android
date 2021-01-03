package com.npu_app.npu_application.model;

import org.chalup.microorm.annotations.Column;

public class Login {
    @Column("user_id")
    private int user_id;
    @Column("username")
    private String username;
    @Column("user_email")
    private String user_email;
    @Column("password")
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
