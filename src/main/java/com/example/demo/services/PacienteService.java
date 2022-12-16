package com.example.demo.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.function.TrimFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.demo.models.Paciente;
import com.example.demo.repositories.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository _pacienteRepository;

    public ArrayList<Paciente> obtenerPacientes() {
        return (ArrayList<Paciente>) _pacienteRepository.findAll();
    }

    public Paciente guardarPaciente(Paciente paciente) throws Exception {

        return _pacienteRepository.save(paciente);

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
                    && paciente.getFechaNacimiento().equals(aux.getFechaNacimiento())) {
                existe = true;
                System.out.println("Existe");
            }

        }

        return existe;
    }

    public boolean eliminarUsuario(Long id) {
        try {
            _pacienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
