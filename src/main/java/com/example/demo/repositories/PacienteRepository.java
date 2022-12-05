package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    // public abstract ArrayList<UsuarioModel> findByNombre(String nombre);
}
