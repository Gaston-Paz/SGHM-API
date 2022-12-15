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

import com.example.demo.models.AltaPaciente;
import com.example.demo.models.Estudio;
import com.example.demo.models.Paciente;
import com.example.demo.services.AntecedenteService;
import com.example.demo.services.ConsultaInicialService;
import com.example.demo.services.EstudioService;
import com.example.demo.services.PacienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService _pacienteService;

    @Autowired
    ConsultaInicialService _ConsultaInicialService;

    @Autowired
    AntecedenteService _AntecedenteService;

    @Autowired
    EstudioService _EstudioService;

    @GetMapping
    public ArrayList<Paciente> obtenerPacientes() {
        return _pacienteService.obtenerPacientes();
    }

    // Enviando el Id sirve como PUT sino es POST
    // @PostMapping
    // public Paciente guardarPaciente(@RequestBody Paciente paciente) {
    // try {
    // return this._pacienteService.guardarPaciente(paciente);
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return new Paciente();
    // }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody AltaPaciente altaPaciente) {
        try {
            Paciente paciente = this._pacienteService.guardarPaciente(altaPaciente.getPaciente());
            altaPaciente.getConsultaInicial().setPaciente(paciente);
            this._ConsultaInicialService.guardarConsulta(altaPaciente.getConsultaInicial());
            altaPaciente.getAntecedente().setPaciente(paciente);
            this._AntecedenteService.guardarAntecedente(altaPaciente.getAntecedente());
            return paciente;
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Paciente();
    }

    @PostMapping("{idPaciente}/{esEstudio}")
    public Paciente guardarFotos(@RequestParam("foto") MultipartFile foto,
            @PathVariable("idPaciente") long idPaciente, @PathVariable("esEstudio") Boolean esEstudio) {
        try {
            if (!esEstudio) {
                if (!foto.isEmpty()) {
                    byte[] bytes = foto.getBytes();
                    Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
                    String ruta = "C://Users//Gastón//Documents//GitHub//SGHM-UI//src//assets//Pacientes//"
                            + paciente.get().getIdPaciente() + "-"
                            + paciente.get().getApellido() + "-" + paciente.get().getNombre();
                    File carpeta = new File(ruta);
                    carpeta.mkdir();
                    Path rutaAbsoluta = Paths.get(ruta + "//Perfil.jpg");
                    Files.write(rutaAbsoluta, bytes);
                    paciente.get().setFotoPerfil(rutaAbsoluta.toString());
                    return _pacienteService.guardarPaciente(paciente.get());
                }
            } else {
                int index = 1;
                if (!foto.isEmpty()) {
                    Estudio estudio = new Estudio();
                    byte[] bytes = foto.getBytes();
                    Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
                    String ruta = "C://Users//Gastón//Documents//GitHub//SGHM-UI//src//assets//Pacientes//"
                            + paciente.get().getIdPaciente() + "-"
                            + paciente.get().getApellido() + "-" + paciente.get().getNombre();
                    File carpeta = new File(ruta);
                    carpeta.mkdir();
                    Path rutaAbsoluta = Paths.get(ruta + "//Estudio-" + index + ".jpg");
                    if (Files.exists(rutaAbsoluta)) {
                        while (Files.exists(rutaAbsoluta)) {
                            index++;
                            rutaAbsoluta = Paths.get(ruta + "//Estudio-" + index + ".jpg");
                        }
                    }
                    Files.write(rutaAbsoluta, bytes);
                    estudio.setPaciente(paciente.get());
                    estudio.setRuta(rutaAbsoluta.toString());
                    _EstudioService.guardarEstudio(estudio);
                    return paciente.get();
                }
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
