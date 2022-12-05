package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Paciente;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.PacienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService _pacienteService;

    @GetMapping
    public ArrayList<Paciente> obtenerPacientes() {
        return _pacienteService.obtenerPacientes();
    }

    // Enviando el Id sirve como PUT sino es POST
    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        try {
            paciente = _pacienteService.guardarPaciente(paciente);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return paciente;
        }
    }

    @GetMapping(path = "/{id}")
    public Optional<Paciente> obtenerPorId(@PathVariable("id") Long id) {
        return _pacienteService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<Paciente> obtenerPorNombre(@RequestParam("Nombre") String Nombre) {
        return _pacienteService.obtenerPorNombre(Nombre);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPaciente(@PathVariable("id") Long id) {
        boolean ok = _pacienteService.eliminarUsuario(id);
        if (ok)
            return "Se eliminó el usuario con Id: " + id;
        else
            return "No se pudo eliminar el usuario con Id: " + id;
    }
}
