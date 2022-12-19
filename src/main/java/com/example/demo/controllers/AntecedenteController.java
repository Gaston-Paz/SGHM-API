package com.example.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Antecedente;
import com.example.demo.services.AntecedenteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/antecedentes")
public class AntecedenteController {
    @Autowired
    AntecedenteService _AntecedenteService;

    @GetMapping
    public ArrayList<Antecedente> obtenerAntecedentes() {
        ArrayList<Antecedente> lista = _AntecedenteService.obtenerAntecedentes();
        return lista;
    }

    @GetMapping("/{id}")
    public Antecedente obtenerPorId(@PathVariable("id") Long id) {
        return _AntecedenteService.obtenerPorId(id);
    }

}
