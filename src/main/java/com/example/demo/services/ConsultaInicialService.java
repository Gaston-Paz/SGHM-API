package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.Paciente;
import com.example.demo.repositories.ConsultaInicialRepository;

@Service
public class ConsultaInicialService {
    @Autowired
    ConsultaInicialRepository _ConsultaInicialRepository;

    public ArrayList<ConsultaInicial> obtenerConsultas() {
        return (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
    }

    public boolean ConsultaExiste(ConsultaInicial consulta) {
        boolean existe = false;
        ArrayList<ConsultaInicial> lista = (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
        Iterator<ConsultaInicial> it = lista.iterator();
        while (it.hasNext()) {
            ConsultaInicial aux = it.next();
            if (consulta.getPaciente().getIdPaciente() == aux.getPaciente().getIdPaciente()) {
                existe = true;
            }

        }

        return existe;
    }

    public ConsultaInicial guardarConsulta(ConsultaInicial consulta) {
        ConsultaInicial con = _ConsultaInicialRepository.save(consulta);
        return con;
    }

    public ConsultaInicial obtenerPorId(Long id) {
        ArrayList<ConsultaInicial> lista = (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
        Iterator<ConsultaInicial> it = lista.iterator();
        while (it.hasNext()) {
            ConsultaInicial aux = it.next();
            if (id == aux.getIdConsulta()) {
                return aux;
            }

        }
        return null;
    }

    public void eliminarConsulta(Paciente paciente) {
        _ConsultaInicialRepository.deleteAllByPaciente(paciente);
    }
}
