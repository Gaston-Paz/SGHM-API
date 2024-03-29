package com.example.demo.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Tratamientos")
@JsonIgnoreProperties({ "paciente" })
public class Tratamiento {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTratamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private Date fecha;

    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String Motivo;

    @Column()
    private String TrianguloDeTalla;

    @Column()
    private String AlturaDeIliacos;

    @Column()
    private String Barral;

    @Column()
    private String Esferas;

    @Column()
    private String Especifico;

    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String Sedestacion;

    @Column(length = Integer.MAX_VALUE)
    private String Sugerencias;

    @Column()
    private Date ProximoTurnoIndicado;

    private long pacienteID;

    public long getPacienteID() {
        return pacienteID;
    }

    public String getSugerencias() {
        return Sugerencias;
    }

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
        return fecha;
    }

    public Long getIdTratamiento() {
        return IdTratamiento;
    }

    public String getMotivo() {
        return Motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Date getProximoTurnoIndicado() {
        return ProximoTurnoIndicado;
    }

    public String getSedestacion() {
        return Sedestacion;
    }

    public long getPacienteId() {
        return this.paciente.getIdPaciente();
    }

    public String getTrianguloDeTalla() {
        return TrianguloDeTalla;
    }

    public void setAlturaDeIliacos(String alturaDeIliacos) {
        AlturaDeIliacos = alturaDeIliacos;
    }

    public void setSugerencias(String sugerencias) {
        Sugerencias = sugerencias;
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
        this.fecha = fecha;
    }

    public void setIdTratamiento(Long idTratamiento) {
        IdTratamiento = idTratamiento;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
