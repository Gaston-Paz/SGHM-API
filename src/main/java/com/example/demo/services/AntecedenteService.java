package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.*;
import com.example.demo.repositories.*;

import jakarta.persistence.EntityNotFoundException;

import com.example.demo.excepciones.*;

@Service
public class AntecedenteService {
    @Autowired
    AntecedenteRepository _AntecedenteRepository;

    public ArrayList<Antecedente> obtenerAntecedentes() {
        return (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
    }

    public boolean AntecedenteExiste(Antecedente antecedente) {
        boolean existe = false;
        ArrayList<Antecedente> lista = (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
        Iterator<Antecedente> it = lista.iterator();
        while (it.hasNext()) {
            Antecedente aux = it.next();
            if (antecedente.getPaciente().getIdPaciente() == aux.getPaciente().getIdPaciente()) {
                existe = true;
            }

        }

        return existe;
    }

    public Antecedente actualizarAntecedente(Antecedente antecedente) {
        Optional<Antecedente> antecedenteExistenteOpcional = _AntecedenteRepository
                .findById(antecedente.getIdAntecedente());
        if (antecedenteExistenteOpcional.isPresent()) {
            Antecedente antecedenteExistente = antecedenteExistenteOpcional.get();
            antecedenteExistente.setAbortosEspontaneo(antecedente.getAbortosEspontaneo());
            antecedenteExistente.setAbortosInducido(antecedente.getAbortosInducido());
            antecedenteExistente.setAccidentes(antecedente.getAccidentes());
            antecedenteExistente.setAlimentacion(antecedente.getAlimentacion());
            antecedenteExistente.setCardiaco(antecedente.getCardiaco());
            antecedenteExistente.setCirugias(antecedente.getCirugias());
            antecedenteExistente.setContencion(antecedente.getContencion());
            antecedenteExistente.setDiabetes(antecedente.getContencion());
            antecedenteExistente.setDigestivo(antecedente.getDigestivo());
            antecedenteExistente.setDolorCabeza(antecedente.getDolorCabeza());
            antecedenteExistente.setDuracion(antecedente.getDuracion());
            antecedenteExistente.setEdadOrtodoncia(antecedente.getEdadOrtodoncia());
            antecedenteExistente.setEmbarazos(antecedente.getEmbarazos());
            antecedenteExistente.setFracturas(antecedente.getFracturas());
            antecedenteExistente.setFrecuencia(antecedente.getFrecuencia());
            antecedenteExistente.setFuma(antecedente.getFuma());
            antecedenteExistente.setIdAntecedente(antecedente.getIdAntecedente());
            antecedenteExistente.setImplanteInferior(antecedente.getImplanteInferior());
            antecedenteExistente.setImplanteSuperior(antecedente.getImplanteSuperior());
            antecedenteExistente.setIntestinal(antecedente.getIntestinal());
            antecedenteExistente.setMedicacion(antecedente.getMedicacion());
            antecedenteExistente.setMenstruacion(antecedente.getMenstruacion());
            antecedenteExistente.setOrtodoncia(antecedente.getOrtodoncia());
            antecedenteExistente.setOseo(antecedente.getOseo());
            antecedenteExistente.setOtrasDrogas(antecedente.getOtrasDrogas());
            antecedenteExistente.setOtros(antecedente.getOtros());
            antecedenteExistente.setPaciente(antecedenteExistente.getPaciente());
            antecedenteExistente.setPartos(antecedente.getPartos());
            antecedenteExistente.setPerdidas(antecedente.getPerdidas());
            antecedenteExistente.setPiezasFaltantesInf(antecedente.getPiezasFaltantesInf());
            antecedenteExistente.setPiezasFaltantesSup(antecedente.getPiezasFaltantesSup());
            antecedenteExistente.setPlacaDescanso(antecedente.getPlacaDescanso());
            antecedenteExistente.setProtesis(antecedente.getProtesis());
            antecedenteExistente.setRespiratorio(antecedente.getRespiratorio());
            antecedenteExistente.setTiroides(antecedente.getTiroides());
            antecedenteExistente.setUrogenital(antecedente.getUrogenital());
            antecedenteExistente.setVolumen(antecedente.getVolumen());
            return _AntecedenteRepository.save(antecedenteExistente);
        } else {
            throw new EntityNotFoundException("No se encontró un antecedente con el ID especificado.");
        }
    }

    public Antecedente obtenerPorId(Long id) {
        ArrayList<Antecedente> lista = (ArrayList<Antecedente>) _AntecedenteRepository.findAll();
        Iterator<Antecedente> it = lista.iterator();
        while (it.hasNext()) {
            Antecedente aux = it.next();
            if (id == aux.getIdAntecedente()) {
                return aux;
            }

        }
        return null;
    }

    public void eliminarAntecedente(Paciente paciente) {
        _AntecedenteRepository.deleteAllByPaciente(paciente);
    }

    public Antecedente guardarAntecedente(Antecedente antecedente) {
        return _AntecedenteRepository.save(antecedente);
    }
}
