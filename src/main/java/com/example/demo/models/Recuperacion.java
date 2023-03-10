package com.example.demo.models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.*;

@Entity
@Table(name = "Recuperaciones")
public class Recuperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdRecuperacion;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    @Value("${Contencion:true}")
    private Boolean Activo;

    @Column(nullable = false)
    private String Token;

    @Column(nullable = false)
    private Date FechaAlta;

    public Recuperacion() {
        this.setFechaAltaNow();
    }

    public void setFechaAlta(Date fechaAlta) {
        FechaAlta = fechaAlta;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setIdRecuperacion(long idRecuperacion) {
        IdRecuperacion = idRecuperacion;
    }

    public Date getFechaAlta() {
        return FechaAlta;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public String getEmail() {
        return Email;
    }

    public long getIdRecuperacion() {
        return IdRecuperacion;
    }

    public String getToken() {
        return Token;
    }

    public void setFechaAltaNow() {
        this.FechaAlta = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Email: " + this.getEmail() + ", Token: " + this.getToken() + ", Activo: " + this.getActivo();
    }

}
