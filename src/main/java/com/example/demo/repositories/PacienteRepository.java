package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

}
