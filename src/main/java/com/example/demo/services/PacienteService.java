package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import com.example.demo.excepciones.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Antecedente;
import com.example.demo.models.Paciente;
import com.example.demo.repositories.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository _pacienteRepository;

    @Autowired
    AntecedenteService _AntecedenteService;

    @Autowired
    ConsultaInicialService _ConsultaInicialService;

    public ArrayList<Paciente> obtenerPacientes() {
        ArrayList<Paciente> lista = (ArrayList<Paciente>) _pacienteRepository.findAll();
        ArrayList<Paciente> listaActiva = new ArrayList<Paciente>();
        Iterator<Paciente> it = lista.iterator();
        while (it.hasNext()) {
            Paciente paciente = it.next();
            if (paciente.getActivo()) {
                listaActiva.add(paciente);
            }
        }
        return listaActiva;
    }

    public Paciente guardarPaciente(Paciente paciente) throws Exception {
        if (PacienteExiste(paciente)) {
            throw new BadRequestException(
                    "Ya existe un paciente con esta combinación de Nombre, Apellido y E-mail: " + paciente.getNombre()
                            + ", " + paciente.getApellido() + " " + paciente.nacimientoToString());
        }
        try {
            Paciente pac = _pacienteRepository.save(paciente);
            return pac;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        try {
            Optional<Paciente> pacienteExistenteOptional = _pacienteRepository.findById(paciente.getIdPaciente());
            if (pacienteExistenteOptional.isPresent()) {
                Paciente pacienteExistente = pacienteExistenteOptional.get();
                pacienteExistente.setActivo(paciente.getActivo());
                pacienteExistente.setNombre(paciente.getNombre());
                pacienteExistente.setApellido(paciente.getApellido());
                pacienteExistente.setCelular(paciente.getCelular());
                pacienteExistente.setDeParte(paciente.getDeParte());
                pacienteExistente.setEmail(paciente.getEmail());
                pacienteExistente.setFechaNacimiento(paciente.getFechaNacimiento());
                pacienteExistente.setLocalidad(paciente.getLocalidad());
                pacienteExistente.setNacio(paciente.getNacio());
                pacienteExistente.setOcupacion(paciente.getOcupacion());
                pacienteExistente.setOtros(paciente.getOtros());
                return _pacienteRepository.save(pacienteExistente);
            } else {
                throw new EntityNotFoundException("No se encontró un paciente con el ID especificado.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return _pacienteRepository.findById(id);
    }

    public ArrayList<Paciente> obtenerPorNombre(String nombre) {
        ArrayList<Paciente> lista = (ArrayList<Paciente>) _pacienteRepository.findAll();
        ArrayList<Paciente> listaPorNombre = new ArrayList<Paciente>();
        Iterator<Paciente> it = lista.iterator();
        while (it.hasNext()) {
            Paciente paciente = it.next();
            if (paciente.getNombre().toUpperCase().trim().equals(nombre.toUpperCase().trim())) {
                listaPorNombre.add(paciente);
            }
        }
        return listaPorNombre;
    }

    public boolean PacienteExiste(Paciente paciente) {
        boolean existe = false;
        ArrayList<Paciente> lista = (ArrayList<Paciente>) _pacienteRepository.findAll();
        Iterator<Paciente> it = lista.iterator();
        while (it.hasNext()) {
            Paciente aux = it.next();
            if (paciente.getNombre().toUpperCase().trim().equals(aux.getNombre().toUpperCase().trim())
                    && paciente.getApellido().toUpperCase().trim().equals(aux.getApellido().toUpperCase().trim())
                    && paciente.getFechaNacimiento().equals(aux.getFechaNacimiento())
                    && paciente.getIdPaciente() == 0) {
                existe = true;
            }

        }
        return existe;
    }

    public void deletePaciente(long id) {
        Optional<Paciente> paciente = obtenerPorId(id);
        _pacienteRepository.delete(paciente.get());
    }
}
