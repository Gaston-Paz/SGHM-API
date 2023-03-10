package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ConsultaInicial;

@Repository
public interface ConsultaInicialRepository extends CrudRepository<ConsultaInicial, Long> {
    // public abstract ArrayList<UsuarioModel> findByNombre(String nombre);
}
