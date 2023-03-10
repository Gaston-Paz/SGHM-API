package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.excepciones.*;
import java.util.Date;
import java.util.Iterator;

@Service
public class TratamientoService {
    @Autowired
    TratamientoRepository _TratamientoRepository;

    public ArrayList<Tratamiento> obtenerTratamientos() {
        return (ArrayList<Tratamiento>) _TratamientoRepository.findAll();
    }

    public Tratamiento guardarTratamiento(Tratamiento tratamiento) {
        try {
            ArrayList<Tratamiento> tratamientosPaciente = this.obtenerPaciente(tratamiento.getPaciente());
            boolean existe = existeTratamiento(tratamientosPaciente, tratamiento);
            if (existe) {
                Conflict con = new Conflict("Ya existe un tratamiento realizado en esta fecha para el paciente.");
                throw con;
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

    public boolean eliminarTratamiento(Long id) {
        try {
            _TratamientoRepository.deleteById(id);
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