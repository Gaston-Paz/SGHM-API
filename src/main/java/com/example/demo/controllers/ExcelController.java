package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ExcelService;

import io.jsonwebtoken.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("api/excel")
public class ExcelController {

    @Autowired
    ExcelService _ExcelService;

    @GetMapping
    public ResponseEntity<byte[]> generarExcel() throws IOException, java.io.IOException {
        return _ExcelService.ExportarHistoria();
    }

}
