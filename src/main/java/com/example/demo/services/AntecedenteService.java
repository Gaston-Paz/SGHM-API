package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.excepciones.*;

@Service
public class AntecedenteService {
    @Autowired
    AntecedenteRepository _AntecedenteRepository;

    public ArrayList<Antecedente> obtenerAntecedentes() {
        return (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
    }

    public boolean AntecedenteExiste(Antecedente antecedente) {
        boolean existe = false;
        ArrayList<Antecedente> lista = (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
        Iterator<Antecedente> it = lista.iterator();
        while (it.hasNext()) {
            Antecedente aux = it.next();
            if (antecedente.getPaciente().getIdPaciente() == aux.getPaciente().getIdPaciente()) {
                existe = true;
            }

        }

        return existe;
    }

    public Antecedente guardarAntecedente(Antecedente antecedente) {
        if (AntecedenteExiste(antecedente)) {
            throw new Conflict(
                    "Ya existen antecedentes cargados para el paciente: " + antecedente.getPaciente().getNombre()
                            + ", " + antecedente.getPaciente().getApellido());
        }
        try {
            Antecedente ant = _AntecedenteRepository.save(antecedente);
            return ant;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
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
