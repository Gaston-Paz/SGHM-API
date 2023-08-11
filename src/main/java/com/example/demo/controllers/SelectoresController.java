package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.LogError;
import com.example.demo.models.PacienteSelector;
import com.example.demo.services.PacienteService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("api/selectores")
public class SelectoresController {
    @Autowired
    PacienteService _pacienteService;

    @GetMapping("/pacientes")
    @Transactional
    public ArrayList<PacienteSelector> get() throws IOException {
        try {
            ArrayList<PacienteSelector> lista = _pacienteService.GetPacientesFromSelector();
            return lista;
        } catch (Exception e) {
            LogError logError = new LogError(e, "GetPacientesFromSelector");
            throw new BadRequestException(
                    "No se pudieron obtener los Pacientes. Si persiste el error comuníquese con el Administrador");
        }
    }

}
