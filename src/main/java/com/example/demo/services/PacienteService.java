package com.example.demo.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Paciente;
import com.example.demo.repositories.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository _pacienteRepository;

    public ArrayList<Paciente> obtenerPacientes() {
        return (ArrayList<Paciente>) _pacienteRepository.findAll();
    }

    public Paciente guardarPaciente(Paciente usuario) {
        return _pacienteRepository.save(usuario);
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return _pacienteRepository.findById(id);
    }

    public ArrayList<Paciente> obtenerPorNombre(String nombre) {
        System.out.println("Service");
        System.out.println(nombre);
        ArrayList<Paciente> lista = (ArrayList<Paciente>) _pacienteRepository.findAll();
        ArrayList<Paciente> listaPorNombre = new ArrayList<Paciente>();
        System.out.println("Listado con usuario: " + listaPorNombre.size());
        for (Paciente paciente : lista) {
            System.out.println("Itera");
            System.out.println(paciente.getNombre());
            if (paciente.getNombre().toUpperCase().trim().equals(nombre.toUpperCase().trim())) {
                listaPorNombre.add(paciente);
                System.out.println("Agrega");
            }
        }
        return listaPorNombre;
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
