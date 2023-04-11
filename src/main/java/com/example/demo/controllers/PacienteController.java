package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.AltaPaciente;
import com.example.demo.models.LogError;
import com.example.demo.models.Paciente;
import com.example.demo.services.AntecedenteService;
import com.example.demo.services.ConsultaInicialService;
import com.example.demo.services.EstudioService;
import com.example.demo.services.PacienteService;
import com.example.demo.services.TratamientoService;

@RestController
@CrossOrigin
@RequestMapping("api/paciente")
public class PacienteController {
    @Autowired
    PacienteService _pacienteService;

    @Autowired
    ConsultaInicialService _ConsultaInicialService;

    @Autowired
    AntecedenteService _AntecedenteService;

    @Autowired
    EstudioService _EstudioService;

    @Autowired
    TratamientoService _TratamientoService;

    @GetMapping
    public ArrayList<Paciente> obtenerPacientes() throws IOException {
        try {
            return _pacienteService.obtenerPacientes();
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Pacientes");
            throw new BadRequestException(
                    "No se pudieron obtener los Pacientes. Si persiste el error comuníquese con el Administrador");
        }
    }

    @Transactional
    @PostMapping
    public Paciente guardarPaciente(@RequestBody AltaPaciente altaPaciente) throws Exception {
        try {
            Paciente paciente = this._pacienteService.guardarPaciente(altaPaciente.getPaciente());
            altaPaciente.getConsultaInicial().setPaciente(paciente);
            this._ConsultaInicialService.guardarConsulta(altaPaciente.getConsultaInicial());
            altaPaciente.getAntecedente().setPaciente(paciente);
            this._AntecedenteService.guardarAntecedente(altaPaciente.getAntecedente());
            altaPaciente.getTratamiento().setPaciente(paciente);
            this._TratamientoService.guardarTratamiento(altaPaciente.getTratamiento());
            return paciente;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Guardar Paciente");
            throw new BadRequestException("Verifique haber completado todos los campos obligatorios");
        }
    }

    @PostMapping(path = "/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciene) throws Exception {
        try {
            Paciente paciente = this._pacienteService.guardarPaciente(paciene);
            return paciente;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Actualizar Paciente");
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Paciente> obtenerPorId(@PathVariable("id") Long id) throws IOException {
        try {
            return _pacienteService.obtenerPorId(id);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Paciente por Id");
            throw new BadRequestException(
                    "No se pudo obtener el Paciente. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping("porNombre/{Nombre}")
    public ArrayList<Paciente> obtenerPorNombre(@PathVariable("Nombre") String Nombre) throws IOException {
        try {
            return _pacienteService.obtenerPorNombre(Nombre);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Paciente por Nombre");
            throw new BadRequestException(
                    "No se pudo obtener los Pacientes. Si persiste el error comuníquese con el Administrador");
        }
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPaciente(@PathVariable("id") Long id) throws IOException {
        try {
            boolean ok = _pacienteService.eliminarUsuario(id);
            if (ok)
                return "Se eliminó el usuario con Id: " + id;
            else
                return "No se pudo eliminar el usuario con Id: " + id;
        } catch (Exception e) {
            // TODO: handle exception
            LogError logError = new LogError(e, "Eliminar Paciente");
            throw e;
        }

    }

    @PostMapping("/{idPaciente}")
    public void guardarFotos(@RequestParam("fotoPerfil") MultipartFile foto,
            @PathVariable("idPaciente") long idPaciente)
            throws Exception {
        try {
            Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
            var fotoBytes = foto.getBytes();
            paciente.get().setFotoPerfil(fotoBytes);
            _pacienteService.guardarPaciente(paciente.get());
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtenter Paciente por Id");
            throw e;
        }

    }

}
