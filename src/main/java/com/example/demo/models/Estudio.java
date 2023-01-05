package com.example.demo.models;

import java.sql.Date;

import javax.persistence.*;

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

    @Column()
    private String Ruta;

    public long getIdEstudio() {
        return IdEstudio;
    }

    public String getRuta() {
        return Ruta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setIdEstudio(long idEstudio) {
        IdEstudio = idEstudio;
    }

    public void setRuta(String ruta) {
        Ruta = ruta;
    }
}
