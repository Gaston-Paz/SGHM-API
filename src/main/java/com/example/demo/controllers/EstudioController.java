package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Estudio;
import com.example.demo.services.EstudioService;
import com.example.demo.services.PacienteService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("api/estudios")
public class EstudioController {
    @Autowired
    EstudioService _EstudioService;

    @Autowired
    PacienteService _pacienteService;

    @Transactional
    @GetMapping
    public ArrayList<Estudio> obtenerEstudios() {
        return _EstudioService.obtenerEstudios();
    }

    @PostMapping("/{idPaciente}/{nombre}")
    public void guardarEstudio(@RequestParam("estudio") MultipartFile estudio,
            @PathVariable("idPaciente") long idPaciente, @PathVariable("nombre") String nombre)
            throws Exception {
        Estudio nuevo = new Estudio();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        nuevo.setFecha(sqlDate);
        nuevo.setPaciente(_pacienteService.obtenerPorId(idPaciente).get());
        var estudioBytes = estudio.getBytes();
        nuevo.setEstudio(estudioBytes);
        nuevo.setNombreArchivo(nombre);
        _EstudioService.guardarEstudio(nuevo);
    }

}
