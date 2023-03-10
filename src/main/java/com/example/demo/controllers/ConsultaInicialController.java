package com.example.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ConsultaInicialService;
import com.example.demo.models.ConsultaInicial;

@RestController
@CrossOrigin
@RequestMapping("api/consulta-inicial")
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

    @PostMapping(path = "/actualizar")
    public ConsultaInicial guardarAntecedente(@RequestBody ConsultaInicial consultaInicial) throws Exception {
        try {
            consultaInicial = this._ConsultaInicialService.guardarConsulta(consultaInicial);
            return consultaInicial;
        } catch (Exception e) {
            throw e;
        }
    }
}
