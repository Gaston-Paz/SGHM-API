package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.LogError;
import com.example.demo.models.Tratamiento;
import com.example.demo.services.ConsultaInicialService;
import com.example.demo.services.TratamientoService;

@RestController
@CrossOrigin
@RequestMapping("api/tratamiento")
public class TratamientoController {
    @Autowired
    TratamientoService _TratamientoService;

    @Autowired
    ConsultaInicialService _ConsultaService;

    @GetMapping
    @ResponseBody
    public ArrayList<Tratamiento> obtenerTratamiento() throws IOException {
        try {
            ArrayList<Tratamiento> lista = _TratamientoService.obtenerTratamientos();
            return lista;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtenter Tratamientos");
            throw new BadRequestException(
                    "No se pudieron obtener los Tratamientos. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping("/{id}")
    public Optional<Tratamiento> obtenerPorId(@PathVariable("id") Long id) throws IOException {
        try {
            return _TratamientoService.obtenerPorId(id);
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtenter Tratamiento por Id");
            throw new BadRequestException(
                    "No se pudo obtener el Tratamiento. Si persiste el error comuníquese con el Administrador");
        }
    }

    @Transactional
    @PostMapping
    public Tratamiento guardarTratamiento(@RequestBody Tratamiento tratamiento) throws Exception {
        try {
            tratamiento = this._TratamientoService.guardarTratamiento(tratamiento, null);
            ArrayList<Tratamiento> tratamientos = _TratamientoService.obtenerPaciente(tratamiento.getPaciente());
            if (tratamiento.getIdTratamiento() == tratamientos.get(0).getIdTratamiento()) {
                ConsultaInicial consulta = _ConsultaService
                        .obtenerPorId(tratamiento.getPaciente().getConsultaInicial().getIdConsulta());
                consulta.setMotivo(tratamiento.getMotivo());
                _ConsultaService.actualizarConsulta(consulta);
            }
            return tratamiento;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Guardar Tratamiento");
            throw e;
        }
    }

}
