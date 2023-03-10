package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Estudios")
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdEstudio;

    @JoinColumn(name = "id_paciente")
    @ManyToOne()
    private Paciente paciente;

    @Column(length = Integer.MAX_VALUE, nullable = false)
    private byte[] Estudio;

    @Column(nullable = false)
    private Date Fecha;

    @Column(nullable = false)
    private String NombreArchivo;

    public Estudio() {
        this.Fecha = new Date(System.currentTimeMillis());
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
