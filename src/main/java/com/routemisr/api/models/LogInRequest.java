package com.routemisr.api.models;

public class LogInRequest {
    private String email;
    private String password;

    public void LogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
