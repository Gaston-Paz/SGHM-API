package com.example.demo.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
    private String rol;

}
