package com.example.demo.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    @Column(unique = true)
    private String email;
    @Nonnull
    private String nombre;
    @Nonnull
    private String apellido;
    @Nonnull
    private String password;
    @Nonnull
    private String rol;

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
}
