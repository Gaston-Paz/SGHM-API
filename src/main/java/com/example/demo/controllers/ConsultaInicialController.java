package com.example.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ConsultaInicialService;
import com.example.demo.excepciones.BadRequestException;
import com.example.demo.excepciones.Conflict;
import com.example.demo.models.ConsultaInicial;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/consulta-inicial")
public class ConsultaInicialController {
    @Autowired
    ConsultaInicialService _ConsultaInicialService;

    @GetMapping
    public ArrayList<ConsultaInicial> obtenerAntecedentes() {
        ArrayList<ConsultaInicial> lista = _ConsultaInicialService.obtenerConsultas();
        return lista;
    }

    @GetMapping("/{id}")
    public ConsultaInicial obtenerPorId(@PathVariable("id") Long id) {
        return _ConsultaInicialService.obtenerPorId(id);
    }
}
