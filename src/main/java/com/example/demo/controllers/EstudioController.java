package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.Estudio;
import com.example.demo.models.LogError;
import com.example.demo.models.Paciente;
import com.example.demo.models.Tratamiento;
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
    public ArrayList<Estudio> obtenerEstudios() throws IOException {
        try {
            return _EstudioService.obtenerEstudios();
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Estudios");
            throw new BadRequestException(
                    "No se pudieron obtener los Estudios. Si persiste el error comuníquese con el Administrador");
        }
    }

    @PostMapping("/{idPaciente}/{nombre}/{tipo}")
    public void guardarEstudio(@RequestParam("estudio") MultipartFile estudio,
            @PathVariable("idPaciente") long idPaciente, @PathVariable("nombre") String nombre,
            @PathVariable("tipo") String tipo)
            throws Exception {
        try {
            Estudio nuevo = new Estudio();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            nuevo.setFecha(sqlDate);
            nuevo.setPaciente(_pacienteService.obtenerPorId(idPaciente).get());
            var estudioBytes = estudio.getBytes();
            nuevo.setEstudio(estudioBytes);
            nuevo.setNombreArchivo(nombre);
            nuevo.setTipo(tipo);
            _EstudioService.guardarEstudio(nuevo);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Guardar Estudio");
            throw e;
        }

    }

    @PatchMapping("/obtenerPorPaciente")
    public ArrayList<Estudio> obtenerPorPaciente(@RequestBody Paciente paciente) throws IOException {
        try {
            return _EstudioService.obtenerPaciente(paciente);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtenter Tratamiento por Paciente");
            throw new BadRequestException(
                    "No se pudo obtener el Estudio. Si persiste el error comuníquese con el Administrador");
        }
    }

}
