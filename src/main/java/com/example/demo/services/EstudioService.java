package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.excepciones.Conflict;
import com.example.demo.models.*;
import com.example.demo.repositories.*;

@Service
public class EstudioService {
    @Autowired
    EstudioRepository _EstudioRepository;

    public ArrayList<Estudio> obtenerEstudios() {
        return (ArrayList<Estudio>) _EstudioRepository.findAll();
    }

    public Estudio guardarEstudio(Estudio estudio) {
        return _EstudioRepository.save(estudio);
    }

    public long obtenerPorId(Long id) {
        ArrayList<Estudio> estudios = (ArrayList<Estudio>) _EstudioRepository.findAll();
        long max = 0;
        Iterator<Estudio> it = estudios.iterator();
        while (it.hasNext()) {
            if (it.next().getIdEstudio() > max) {
                max = it.next().getIdEstudio();
            }
        }
        max++;
        return max;
    }

    public void eliminarEstudio(Paciente paciente) {
        _EstudioRepository.deleteAllByPaciente(paciente);

    }

    public ArrayList<Estudio> obtenerPaciente(Paciente paciente) {
        return _EstudioRepository.findAllByPaciente(paciente);
    }
}
