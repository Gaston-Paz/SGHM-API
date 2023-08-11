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
import com.example.demo.models.Antecedente;
import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.LogError;
import com.example.demo.models.Paciente;
import com.example.demo.models.PacienteSelector;
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

    @GetMapping("/paginado/paciente/{pagina}/{nombre}")
    @Transactional
    public ArrayList<Paciente> obtenerPacientesPaginado(@PathVariable("pagina") Long pagina,
            @PathVariable("nombre") String nombre) throws IOException {
        try {
            return _pacienteService.getPacientesPaginado(pagina, nombre);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Pacientes");
            throw new BadRequestException(
                    "No se pudieron obtener los Pacientes. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping(path = "Selector")
    public ArrayList<PacienteSelector> GetPacientesFromSelector() throws IOException {
        try {
            ArrayList<PacienteSelector> lsita = _pacienteService.GetPacientesFromSelector();
            System.out.println(lsita.size());
            System.out.println(lsita.get(0).getApellido());
            return lsita;
        } catch (Exception e) {
            LogError logError = new LogError(e, "GetPacientesFromSelector");
            throw new BadRequestException(
                    "No se pudieron obtener los Pacientes. Si persiste el error comuníquese con el Administrador");
        }
    }

    @Transactional
    @PostMapping
    public Paciente guardarPaciente(@RequestBody AltaPaciente altaPaciente) throws Exception {
        try {
            altaPaciente.getPaciente().setActivo(true);
            Paciente paciente = this._pacienteService.guardarPaciente(altaPaciente.getPaciente());
            System.out.println(paciente.getIdPaciente());
            altaPaciente.getConsultaInicial().setPaciente(paciente);
            altaPaciente.getConsultaInicial().setIdConsulta(paciente.getIdPaciente());
            ConsultaInicial consulta = this._ConsultaInicialService.guardarConsulta(altaPaciente.getConsultaInicial());

            altaPaciente.getAntecedente().setPaciente(paciente);
            altaPaciente.getAntecedente().setIdAntecedente(paciente.getIdPaciente());
            Antecedente antecedente = this._AntecedenteService.guardarAntecedente(altaPaciente.getAntecedente());

            paciente.setAntecedente(antecedente);
            paciente.setConsultaInicial(consulta);
            this._pacienteService.actualizarPaciente(paciente);

            altaPaciente.getTratamiento().setPaciente(paciente);
            this._TratamientoService.guardarTratamiento(altaPaciente.getTratamiento(),
                    altaPaciente.getConsultaInicial());

            return paciente;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Guardar Paciente");
            throw new BadRequestException("Verifique haber completado todos los campos obligatorios");
        }
    }

    @PostMapping(path = "/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) throws Exception {
        try {
            return this._pacienteService.actualizarPaciente(paciente);
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

    @Transactional
    @PostMapping(path = "/eliminar")
    public ArrayList<Paciente> eliminarPaciente(@RequestBody Paciente paciente) throws IOException {
        try {
            paciente.setActivo(false);
            this._pacienteService.actualizarPaciente(paciente);
            return _pacienteService.obtenerPacientes();
        } catch (Exception e) {
            // TODO: handle exception
            LogError logError = new LogError(e, "Eliminar Paciente");
            throw e;
        }

    }

    // @PostMapping("/{idPaciente}")
    // public void guardarFotos(@RequestParam("fotoPerfil") MultipartFile foto,
    // @PathVariable("idPaciente") long idPaciente)
    // throws Exception {
    // try {
    // Optional<Paciente> paciente = _pacienteService.obtenerPorId(idPaciente);
    // var fotoBytes = foto.getBytes();
    // paciente.get().setFotoPerfil(fotoBytes);
    // _pacienteService.guardarPaciente(paciente.get());
    // } catch (Exception e) {
    // LogError logError = new LogError(e, "Obtenter Paciente por Id");
    // throw e;
    // }

    // }

}
