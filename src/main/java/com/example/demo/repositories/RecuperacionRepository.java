package com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Recuperacion;

@Repository
public interface RecuperacionRepository extends CrudRepository<Recuperacion, Long> {

}
