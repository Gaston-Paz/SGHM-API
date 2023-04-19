package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
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
        Antecedente ant = _AntecedenteRepository.save(antecedente);
        return ant;
    }

    public Antecedente obtenerPorId(Long id) {
        ArrayList<Antecedente> lista = (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
        Iterator<Antecedente> it = lista.iterator();
        while (it.hasNext()) {
            Antecedente aux = it.next();
            if (id == aux.getIdAntecedente()) {
                return aux;
            }

        }
        return null;
    }

    public void eliminarAntecedente(Paciente paciente) {
        _AntecedenteRepository.deleteAllByPaciente(paciente);
    }
}
