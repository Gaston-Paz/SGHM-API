package com.example.demo.controllers;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Estudio;
import com.example.demo.services.EstudioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/estudios")
public class EstudioController {
    @Autowired
    EstudioService _EstudioService;

    @Transactional
    @GetMapping
    public ArrayList<Estudio> obtenerEstudios() {
        return _EstudioService.obtenerEstudios();
    }

}
