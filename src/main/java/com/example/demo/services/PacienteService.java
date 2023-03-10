package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import com.example.demo.excepciones.*;
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

    public Paciente guardarPaciente(Paciente paciente) throws Exception {
        if (PacienteExiste(paciente)) {
            throw new Conflict(
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
            return _pacienteRepository.save(paciente);
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
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

    public boolean eliminarUsuario(Long id) {
        try {
            _pacienteRepository.deleteById(id);
            return true;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
    }
}
