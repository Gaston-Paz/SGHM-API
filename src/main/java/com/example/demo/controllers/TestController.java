package com.example.demo.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.LogError;
import com.example.demo.security.AuthCredentials;
import com.example.demo.security.TokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public String Test(HttpServletRequest request) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = authHeader.replace("Bearer ", ""); // remover "Bearer " del encabezado
            UsernamePasswordAuthenticationToken authentication = TokenUtils.getAuthentication(token);
            return "ROL: " + authentication.getCredentials().toString();
            // return "Backend funcionado";
        } catch (Exception e) {
            // TODO: handle exception
            LogError logError = new LogError(e, "Test");
            throw e;
        }
    }

}
