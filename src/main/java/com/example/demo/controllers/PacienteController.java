package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.Entity;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Foto;
import com.example.demo.models.Paciente;
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
            return this._pacienteService.guardarPaciente(paciente);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Paciente();
    }

    // @PostMapping("{idPaciente}")
    // public Paciente guardarFoto(@RequestParam("foto") MultipartFile foto,
    // @PathVariable("idPaciente") long idPaciente) {
    // try {
    // if (!foto.isEmpty()) {
    // byte[] bytes = foto.getBytes();
    // Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
    // System.out.println(paciente.get().getApellido());
    // paciente.get().setFotoPerfil(bytes);
    // _pacienteService.guardarPaciente(paciente.get());
    // return paciente.get();
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return new Paciente();
    // }

    @PostMapping("{idPaciente}")
    public Paciente guardarFotos(@RequestParam("foto") MultipartFile foto,
            @PathVariable("idPaciente") long idPaciente) {
        try {
            if (!foto.isEmpty()) {
                byte[] bytes = foto.getBytes();
                Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
                String ruta = "C://SGHC//Pacientes//" + paciente.get().getApellido() + "-" + paciente.get().getNombre();
                File carpeta = new File(ruta);
                carpeta.mkdir();
                Path rutaAbsoluta = Paths.get(ruta + "//Perfil-" + paciente.get().getIdPaciente() + ".jpg");
                Files.write(rutaAbsoluta, bytes);
                paciente.get().setFotoPerfil(rutaAbsoluta.toString());
                return _pacienteService.guardarPaciente(paciente.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Paciente();
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
