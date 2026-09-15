package com.routemisr.api.models;

public class SignUpRequest {

    private String name;
    private String password;
    private String email;
    private String rePassword;
    private String phone;

    public SignUpRequest(String name, String password, String email, String rePassword, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.rePassword = rePassword;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getEmail() {
        return email;
    }

}
