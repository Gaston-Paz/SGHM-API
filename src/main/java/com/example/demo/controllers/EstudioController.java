package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.EstudioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/estudios")
public class EstudioController {
    @Autowired
    EstudioService _EstudioService;
}
