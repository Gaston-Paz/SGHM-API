package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.Paciente;
import com.example.demo.models.Tratamiento;
import com.example.demo.repositories.ConsultaInicialRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaInicialService {
    @Autowired
    ConsultaInicialRepository _ConsultaInicialRepository;

    @Autowired
    TratamientoService _TratamientoService;

    public ArrayList<ConsultaInicial> obtenerConsultas() {
        return (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
    }

    public boolean ConsultaExiste(ConsultaInicial consulta) {
        boolean existe = false;
        ArrayList<ConsultaInicial> lista = (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
        Iterator<ConsultaInicial> it = lista.iterator();
        while (it.hasNext()) {
            ConsultaInicial aux = it.next();
            if (consulta.getPaciente().getIdPaciente() == aux.getPaciente().getIdPaciente()) {
                existe = true;
            }

        }

        return existe;
    }

    public ConsultaInicial guardarConsulta(ConsultaInicial consulta) {
        ConsultaInicial con = _ConsultaInicialRepository.save(consulta);
        return con;
    }

    public ConsultaInicial obtenerPorId(Long id) {
        ArrayList<ConsultaInicial> lista = (ArrayList<ConsultaInicial>) _ConsultaInicialRepository.findAll();
        Iterator<ConsultaInicial> it = lista.iterator();
        while (it.hasNext()) {
            ConsultaInicial aux = it.next();
            if (id == aux.getIdConsulta()) {
                return aux;
            }

        }
        return null;
    }

    public void eliminarConsulta(Paciente paciente) {
        _ConsultaInicialRepository.deleteAllByPaciente(paciente);
    }

    public ConsultaInicial actualizarConsulta(ConsultaInicial consulta) {
        try {
            Optional<ConsultaInicial> consultaInicialExistenteOpcional = _ConsultaInicialRepository
                    .findById(consulta.getIdConsulta());
            if (consultaInicialExistenteOpcional.isPresent()) {
                ConsultaInicial consultaInicialExistente = consultaInicialExistenteOpcional.get();
                consultaInicialExistente.setActividadFisica(consulta.getActividadFisica());
                consultaInicialExistente.setAntiguedad(consulta.getAntiguedad());
                consultaInicialExistente.setAtenua(consulta.getAtenua());
                consultaInicialExistente.setCaracteristica(consulta.getCaracteristica());
                consultaInicialExistente.setCovid(consulta.getCovid());
                consultaInicialExistente.setFecha(consulta.getFecha());
                consultaInicialExistente.setFechaCovid(consulta.getFechaCovid());
                consultaInicialExistente.setIdConsulta(consulta.getIdConsulta());
                consultaInicialExistente.setIntensidad(consulta.getIntensidad());
                consultaInicialExistente.setIrradiacion(consulta.getIrradiacion());
                consultaInicialExistente.setLocalizacion(consulta.getLocalizacion());
                consultaInicialExistente.setMotivo(consulta.getMotivo());
                consultaInicialExistente.setOtros(consulta.getOtros());
                consultaInicialExistente.setPaciente(consultaInicialExistenteOpcional.get().getPaciente());

                // Tratamiento
                Tratamiento tratamiento = _TratamientoService.obtenerPaciente(consultaInicialExistente.getPaciente())
                        .get(0);
                tratamiento.setMotivo(consultaInicialExistente.getMotivo());
                _TratamientoService.actualizarTratamiento(tratamiento);

                return _ConsultaInicialRepository.save(consultaInicialExistente);
            } else {
                throw new EntityNotFoundException("No se encontró una consulta inicial con el ID especificado.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
