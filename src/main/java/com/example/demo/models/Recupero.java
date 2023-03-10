package com.example.demo.models;

public class Recupero {
    private String Email;
    private String Token;
    private String Password;

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getToken() {
        return Token;
    }
}
