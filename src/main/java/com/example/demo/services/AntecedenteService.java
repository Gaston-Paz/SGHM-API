package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.*;
import com.example.demo.repositories.*;

@Service
public class AntecedenteService {
    @Autowired
    AntecedenteRepository _AntecedenteRepository;

    public ArrayList<Antecedente> obtenerAntecedentes() {
        return (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
    }

    public Antecedente guardarAntecedente(Antecedente antecedente) {
        return _AntecedenteRepository.save(antecedente);
    }

    public Optional<Antecedente> obtenerPorId(Long id) {
        return _AntecedenteRepository.findById(id);
    }

    public boolean eliminarAntecedente(Long id) {
        try {
            _AntecedenteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
