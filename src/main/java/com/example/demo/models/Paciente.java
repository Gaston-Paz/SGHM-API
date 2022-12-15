package com.example.demo.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdPaciente;

    @Column(nullable = false)
    private String Nombre;

    @Column(nullable = false)
    private String Apellido;

    @Column(nullable = false)
    private String Localidad;

    @Column(nullable = false)
    private String Ocupacion;

    @Column(nullable = false)
    private String Celular;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private Date FechaNacimiento;

    @Column(nullable = false)
    private String Nacio;

    @Column()
    private String FotoPerfil;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ConsultaInicial consultaInicial;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Antecedente antecedente;

    public String getApellido() {
        return Apellido;
    }

    public String getCelular() {
        return Celular;
    }

    public String getEmail() {
        return Email;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getFotoPerfil() {
        return FotoPerfil;
    }

    public long getIdPaciente() {
        return IdPaciente;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public String getNacio() {
        return Nacio;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public void setFotoPerfil(String fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }

    public void setIdPaciente(long idPaciente) {
        IdPaciente = idPaciente;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public void setNacio(String nacio) {
        Nacio = nacio;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public Paciente() {

    }

}
