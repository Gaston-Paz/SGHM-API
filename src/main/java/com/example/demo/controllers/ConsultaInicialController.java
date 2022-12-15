package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ConsultaInicialService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/consulta-inicial")
public class ConsultaInicialController {
    @Autowired
    ConsultaInicialService _ConsultaInicialService;

}
