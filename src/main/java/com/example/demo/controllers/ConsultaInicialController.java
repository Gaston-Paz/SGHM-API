package com.example.demo.controllers;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ConsultaInicialService;
import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.LogError;

@RestController
@CrossOrigin
@RequestMapping("api/consulta-inicial")
public class ConsultaInicialController {
    @Autowired
    ConsultaInicialService _ConsultaInicialService;

    @GetMapping
    public ArrayList<ConsultaInicial> obtenerConsultas() throws IOException {
        try {
            ArrayList<ConsultaInicial> lista = _ConsultaInicialService.obtenerConsultas();
            return lista;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Consultas Iniciales");
            throw new BadRequestException(
                    "No se pudieron obtener las Consultas Iniciales. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping("/{id}")
    public ConsultaInicial obtenerPorId(@PathVariable("id") Long id) throws IOException {
        try {
            return _ConsultaInicialService.obtenerPorId(id);

        } catch (Exception e) {
            LogError logError = new LogError(e, "Obtener Consultas Iniciales por Id");
            throw new BadRequestException(
                    "No se pudo obtener la Consulta Inicial. Si persiste el error comuníquese con el Administrador");
        }
    }

    @PostMapping(path = "/actualizar")
    public ConsultaInicial guardarAntecedente(@RequestBody ConsultaInicial consultaInicial) throws Exception {
        try {
            consultaInicial = this._ConsultaInicialService.guardarConsulta(consultaInicial);
            return consultaInicial;
        } catch (Exception e) {
            LogError logError = new LogError(e, "Actualizar Consultas Iniciales");
            throw e;
        }
    }
}
