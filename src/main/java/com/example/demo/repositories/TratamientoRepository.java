package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Tratamiento;

@Repository
public interface TratamientoRepository extends CrudRepository<Tratamiento, Long> {

}
