package com.example.demo.models;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Tratamientos")
public class Tratamiento {

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTratamiento;

    @JoinColumn(name = "id_paciente")
    @ManyToOne()
    private Paciente paciente;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
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

    @Column(nullable = false)
    private String Sedestacion;

    @Column()
    private String Sugerencias;

    @Column()
    private Date ProximoTurnoIndicado;

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
