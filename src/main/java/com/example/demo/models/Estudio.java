package com.example.demo.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Estudios")
@JsonIgnoreProperties({ "paciente" })
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdEstudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(length = Integer.MAX_VALUE, nullable = false)
    private byte[] Estudio;

    @Column(nullable = false)
    private Date Fecha;

    @Column(nullable = false)
    private String NombreArchivo;

    @Column(nullable = false)
    private String Tipo;

    public Estudio() {
        this.Fecha = new Date(System.currentTimeMillis());
    }

    public String getTipo() {
        return Tipo;
    }

    public String getNombreArchivo() {
        return NombreArchivo;
    }

    public Date getFecha() {
        return Fecha;
    }

    public long getIdEstudio() {
        return IdEstudio;
    }

    public byte[] getEstudio() {
        return Estudio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public long getPacienteId() {
        return this.paciente.getIdPaciente();
    }

    public void setNombreArchivo(String nombreArchivo) {
        NombreArchivo = nombreArchivo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setIdEstudio(long idEstudio) {
        IdEstudio = idEstudio;
    }

    public void setEstudio(byte[] estudio) {
        Estudio = estudio;
    }
}
