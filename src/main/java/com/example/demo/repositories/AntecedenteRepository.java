package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Antecedente;

@Repository
public interface AntecedenteRepository extends CrudRepository<Antecedente, Long> {

}
