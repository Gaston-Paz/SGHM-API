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
public class TratamientoService {
    @Autowired
    TratamientoRepository _TratamientoRepository;

    public ArrayList<Tratamiento> obtenerAntecedentes() {
        return (ArrayList<Tratamiento>) _TratamientoRepository.findAll();
    }

    public Tratamiento guardarTratamiento(Tratamiento tratamiento) {
        try {
            Tratamiento ant = _TratamientoRepository.save(tratamiento);
            return ant;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Tratamiento> obtenerPorId(Long id) {
        return _TratamientoRepository.findById(id);
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
