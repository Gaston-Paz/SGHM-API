package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.Paciente;

@Repository
public interface ConsultaInicialRepository extends CrudRepository<ConsultaInicial, Long> {
    ConsultaInicial findByPaciente(Paciente paciente);
}
