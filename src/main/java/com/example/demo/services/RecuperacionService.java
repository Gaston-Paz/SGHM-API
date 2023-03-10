package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Recuperacion;
import com.example.demo.repositories.RecuperacionRepository;

@Service
public class RecuperacionService {
    @Autowired
    RecuperacionRepository _RecuperacionRepository;

    public Recuperacion obtenerPorEmail(String email) {
        ArrayList<Recuperacion> lista = (ArrayList<Recuperacion>) _RecuperacionRepository.findAll();
        Iterator<Recuperacion> it = lista.iterator();
        while (it.hasNext()) {
            Recuperacion aux = it.next();
            if (email.toUpperCase().trim().equals(aux.getEmail().toUpperCase().trim()) && aux.getActivo()) {
                return aux;
            }

        }
        return null;
    }

    public Recuperacion GuardarRecuperacion(Recuperacion recuperacion) {
        Recuperacion recuperacionActiva = this.obtenerPorEmail(recuperacion.getEmail());
        if (recuperacionActiva == null) {
            recuperacionActiva = new Recuperacion();
            recuperacionActiva.setEmail(recuperacion.getEmail());
            recuperacionActiva.setActivo(true);
            recuperacionActiva.setToken(recuperacion.getToken());
        } else if (!recuperacionActiva.getActivo()) {
            recuperacionActiva = new Recuperacion();
            recuperacionActiva.setEmail(recuperacion.getEmail());
            recuperacionActiva.setActivo(true);
            recuperacionActiva.setToken(recuperacion.getToken());
        }
        recuperacionActiva = _RecuperacionRepository.save(recuperacion);
        _RecuperacionRepository.save(recuperacionActiva);
        return recuperacionActiva;
    }

}
