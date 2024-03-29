package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.*;
import com.example.demo.repositories.*;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.excepciones.*;
import java.util.Date;
import java.util.Iterator;

@Service
public class TratamientoService {
    @Autowired
    TratamientoRepository _TratamientoRepository;

    @Autowired
    ConsultaInicialRepository _ConsultaInicialRepository;

    public ArrayList<Tratamiento> obtenerTratamientos() {
        return (ArrayList<Tratamiento>) _TratamientoRepository.findAll();
    }

    public Tratamiento guardarTratamiento(Tratamiento tratamiento,
            @RequestParam(required = false) ConsultaInicial consulta) {
        try {
            ArrayList<Tratamiento> tratamientosPaciente = this.obtenerPaciente(tratamiento.getPaciente());
            boolean existe = existeTratamiento(tratamientosPaciente, tratamiento);
            if (existe) {
                throw new BadRequestException("Ya existe un tratamiento realizado en esta fecha para el paciente.");
            }

            if (consulta == null) {
                Optional<ConsultaInicial> consultaInicial = _ConsultaInicialRepository
                        .findById(tratamiento.getPacienteID());
                if (consultaInicial.get().getFecha().compareTo(tratamiento.getFecha()) > 0) {
                    throw new BadRequestException(
                            "La fecha del nuevo tratamiento no puede ser anterior a la fecha de la primera consulta.");
                }
                tratamiento.setPaciente(consultaInicial.get().getPaciente());
            }
            tratamiento = _TratamientoRepository.save(tratamiento);
            return tratamiento;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean existeTratamiento(ArrayList<Tratamiento> tratamientosPaciente, Tratamiento tratamientoEnviado) {
        Iterator<Tratamiento> it = tratamientosPaciente.iterator();
        while (it.hasNext()) {
            Tratamiento tratamiento = it.next();
            String fechaBaseCortada = armarFecha(tratamiento.getFecha());
            String fechaFrontCortada = armarFecha(tratamientoEnviado.getFecha());

            if (fechaBaseCortada.equals(fechaFrontCortada)
                    && tratamiento.getIdTratamiento() != tratamientoEnviado.getIdTratamiento()) {
                return true;
            }
        }
        return false;
    }

    public String armarFecha(Date fecha) {
        Date fechaBase = new Date(fecha.getTime());
        String[] fechaBaseSinCortar = fechaBase.toString().split(" ");
        return fechaBaseSinCortar[1] + " " + fechaBaseSinCortar[2] + " " + fechaBaseSinCortar[5];
    }

    public Optional<Tratamiento> obtenerPorId(Long id) {
        return _TratamientoRepository.findById(id);
    }

    public ArrayList<Tratamiento> obtenerPaciente(Paciente paciente) {
        return _TratamientoRepository.findAllByPaciente(paciente);
    }

    public void eliminarTratamiento(Paciente paciente) {
        _TratamientoRepository.deleteAllByPaciente(paciente);

    }

    public Tratamiento actualizarTratamiento(Tratamiento tratamiento) {
        try {
            Optional<Tratamiento> tratamientoExitenteOpcional = _TratamientoRepository
                    .findById(tratamiento.getIdTratamiento());

            if (tratamientoExitenteOpcional.isPresent()) {
                Tratamiento tratamientoExitente = tratamientoExitenteOpcional.get();
                tratamientoExitente.setAlturaDeIliacos(tratamiento.getAlturaDeIliacos());
                tratamientoExitente.setBarral(tratamiento.getBarral());
                tratamientoExitente.setEsferas(tratamiento.getEsferas());
                tratamientoExitente.setFecha(tratamiento.getFecha());
                tratamientoExitente.setIdTratamiento(tratamiento.getIdTratamiento());
                tratamientoExitente.setMotivo(tratamiento.getMotivo());
                tratamientoExitente.setPaciente(tratamientoExitenteOpcional.get().getPaciente());
                tratamientoExitente.setProximoTurnoIndicado(tratamiento.getProximoTurnoIndicado());
                tratamientoExitente.setSedestacion(tratamiento.getSedestacion());
                tratamientoExitente.setSugerencias(tratamiento.getSugerencias());
                tratamientoExitente.setTrianguloDeTalla(tratamiento.getTrianguloDeTalla());
                return _TratamientoRepository.save(tratamientoExitente);
            } else {
                throw new EntityNotFoundException("No se encontró un tratamiento con el ID especificado.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
