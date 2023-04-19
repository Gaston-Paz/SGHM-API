package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Antecedente;
import com.example.demo.models.Paciente;

@Repository
public interface AntecedenteRepository extends CrudRepository<Antecedente, Long> {
    void deleteAllByPaciente(Paciente paciente);
}
