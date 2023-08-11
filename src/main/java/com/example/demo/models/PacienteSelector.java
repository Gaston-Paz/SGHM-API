package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class PacienteSelector {

    @Id
    private long IdPaciente;
    @Column
    private String Nombre;
    @Column
    private String Apellido;

    public PacienteSelector(long id, String nombre, String apellido) {
        this.setIdPaciente(id);
        this.setNombre(nombre);
        this.setApellido(apellido);
    }

    private void setApellido(String apellido2) {
        this.Apellido = apellido2;
    }

    private void setIdPaciente(long id) {
        this.IdPaciente = id;
    }

    private void setNombre(String nombre2) {
        this.Nombre = nombre2;
    }

    public String getApellido() {
        return Apellido;
    }

    public long getIdPaciente() {
        return IdPaciente;
    }

    public String getNombre() {
        return Nombre;
    }
}
