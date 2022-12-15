package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ConsultaInicial;
import com.example.demo.repositories.ConsultaInicialRepository;

@Service
public class ConsultaInicialService {
    @Autowired
    ConsultaInicialRepository _ConsultaInicialRepository;

    public ArrayList<ConsultaInicial> obtenerConsultas() {
        return (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
    }

    public ConsultaInicial guardarConsulta(ConsultaInicial consulta) {
        return _ConsultaInicialRepository.save(consulta);
    }

    public Optional<ConsultaInicial> obtenerPorId(Long id) {
        return _ConsultaInicialRepository.findById(id);
    }

    public boolean eliminarConsulta(Long id) {
        try {
            _ConsultaInicialRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
