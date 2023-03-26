package com.example.demo.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excepciones.BadRequestException;
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
        try {
            ArrayList<Antecedente> lista = _AntecedenteService.obtenerAntecedentes();
            return lista;
        } catch (Exception e) {
            throw new BadRequestException(
                    "No se pudieron obtener los Antecedetes. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping("/{id}")
    public Antecedente obtenerPorId(@PathVariable("id") Long id) {
        try {
            return _AntecedenteService.obtenerPorId(id);
        } catch (Exception e) {
            throw new BadRequestException(
                    "No se pudo obtener el Antecedente. Si persiste el error comuníquese con el Administrador");
        }
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
