package com.example.demo.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.LogError;

@RestController
@CrossOrigin
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public String Test() throws IOException {
        try {
            throw new BadRequestException("Se rompio todo");
            // return "Backend funcionado";
        } catch (Exception e) {
            // TODO: handle exception
            LogError logError = new LogError(e, "Test");
            throw e;
        }
    }

}
