package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Tratamiento;
import com.example.demo.services.TratamientoService;

@RestController
@CrossOrigin
@RequestMapping("api/tratamiento")
public class TratamientoController {
    @Autowired
    TratamientoService _TratamientoService;

    @GetMapping
    public ArrayList<Tratamiento> obtenerTratamiento() {
        ArrayList<Tratamiento> lista = _TratamientoService.obtenerTratamientos();
        return lista;
    }

    @GetMapping("/{id}")
    public Optional<Tratamiento> obtenerPorId(@PathVariable("id") Long id) {
        return _TratamientoService.obtenerPorId(id);
    }

    @Transactional
    @PostMapping
    public Tratamiento guardarTratamiento(@RequestBody Tratamiento tratamiento) throws Exception {
        try {
            tratamiento = this._TratamientoService.guardarTratamiento(tratamiento);
            return tratamiento;
        } catch (Exception e) {
            throw e;
        }
    }

}
