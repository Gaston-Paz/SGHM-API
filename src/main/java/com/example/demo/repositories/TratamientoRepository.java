package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Paciente;
import com.example.demo.models.Tratamiento;

import java.util.ArrayList;

@Repository
public interface TratamientoRepository extends CrudRepository<Tratamiento, Long> {
    ArrayList<Tratamiento> findAllByPaciente(Paciente paciente);

    void deleteAllByPaciente(Paciente paciente);
}
