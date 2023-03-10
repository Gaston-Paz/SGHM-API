package com.example.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Antecedente;
import com.example.demo.services.AntecedenteService;

@RestController
@CrossOrigin
@RequestMapping("api/antecedentes")
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

    @PostMapping(path = "/actualizar")
    public Antecedente guardarAntecedente(@RequestBody Antecedente antecedente) throws Exception {
        try {
            antecedente = this._AntecedenteService.guardarAntecedente(antecedente);
            return antecedente;
        } catch (Exception e) {
            throw e;
        }
    }

}
