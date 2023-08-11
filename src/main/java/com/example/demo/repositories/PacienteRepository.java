package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

    @Procedure(name = "sghc.getPacientesSelector()")
    ArrayList<Object[]> getPacientesSelector();

    @Procedure(name = "sghc.getPacientesPaginado()")
    ArrayList<Object[]> getPacientesPaginado(@Param("pagina") Long pagina, @Param("nombre") String nombre);
}
