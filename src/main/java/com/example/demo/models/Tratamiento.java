package com.example.demo.models;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Tratamientos")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTratamiento;

    @JoinColumn(name = "id_paciente", unique = true)
    @OneToOne()
    private Paciente Paciente;

    private Date Fecha;
    private String Motivo;
    private String TrianguloDeTalla;
    private String AlturaDeIliacos;
    private String Barral;
    private String Esferas;
    private String Especifico;
    private String Sedestacion;
    private Date ProximoTurnoIndicado;

    public String getAlturaDeIliacos() {
        return AlturaDeIliacos;
    }

    public String getBarral() {
        return Barral;
    }

    public String getEsferas() {
        return Esferas;
    }

    public String getEspecifico() {
        return Especifico;
    }

    public Date getFecha() {
        return Fecha;
    }

    public Long getIdTratamiento() {
        return IdTratamiento;
    }

    public String getMotivo() {
        return Motivo;
    }

    public Paciente getPaciente() {
        return Paciente;
    }

    public Date getProximoTurnoIndicado() {
        return ProximoTurnoIndicado;
    }

    public String getSedestacion() {
        return Sedestacion;
    }

    public String getTrianguloDeTalla() {
        return TrianguloDeTalla;
    }

    public void setAlturaDeIliacos(String alturaDeIliacos) {
        AlturaDeIliacos = alturaDeIliacos;
    }

    public void setBarral(String barral) {
        Barral = barral;
    }

    public void setEsferas(String esferas) {
        Esferas = esferas;
    }

    public void setEspecifico(String especifico) {
        Especifico = especifico;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public void setIdTratamiento(Long idTratamiento) {
        IdTratamiento = idTratamiento;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public void setPaciente(Paciente paciente) {
        Paciente = paciente;
    }

    public void setProximoTurnoIndicado(Date proximoTurnoIndicado) {
        ProximoTurnoIndicado = proximoTurnoIndicado;
    }

    public void setSedestacion(String sedestacion) {
        Sedestacion = sedestacion;
    }

    public void setTrianguloDeTalla(String trianguloDeTalla) {
        TrianguloDeTalla = trianguloDeTalla;
    }
}
