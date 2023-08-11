package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Pacientes")
@JsonIgnoreProperties({ "consultaInicial", "antecedente", "tratamientos", "estudios" })
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdPaciente;

    @Column(nullable = false)
    private boolean Activo;

    @Column(nullable = false)
    private String Nombre;

    @Column(nullable = false)
    private String Apellido;

    @Column(nullable = true)
    private String Localidad;

    @Column(nullable = true)
    private String Ocupacion;

    @Column(nullable = true)
    private String Celular;

    @Column(nullable = true)
    private String Email;

    @Column(nullable = false)
    private Date FechaNacimiento;

    @Column(nullable = true)
    private String Otros;

    @Column(nullable = true)
    private String DeParte;

    @Column(nullable = false)
    private String Nacio;

    @Column(length = Integer.MAX_VALUE)
    private byte[] FotoPerfil;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consultaInicial_id", referencedColumnName = "id")
    private ConsultaInicial consultaInicial;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "antecedente_id", referencedColumnName = "id")
    private Antecedente antecedente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Tratamiento> tratamientos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paciente_id")
    private List<Estudio> estudios = new ArrayList<>();

    public Paciente(long id, boolean activo, String apellido, String celular, String deParte, String email,
            Date fechaNac, byte[] foto, String localidad, String nacio, String nombre, String ocupacion, String otros) {
        this.setIdPaciente(id);
        this.setActivo(activo);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCelular(celular);
        this.setDeParte(deParte);
        this.setEmail(email);
        this.setFechaNacimiento(fechaNac);
        this.setFotoPerfil(foto);
        this.setLocalidad(localidad);
        this.setNacio(nacio);
        this.setOcupacion(ocupacion);
        this.setOtros(otros);
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    public void setConsultaInicial(ConsultaInicial consultaInicial) {
        this.consultaInicial = consultaInicial;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public String getCelular() {
        return Celular;
    }

    public boolean getActivo() {
        return Activo;
    }

    public String getDeParte() {
        return DeParte;
    }

    public String getOtros() {
        return Otros;
    }

    public String getEmail() {
        return Email;
    }

    public String nacimientoToString() {
        String[] vec = FechaNacimiento.toString().split(" ");
        String fecha = vec[2] + "/" + MapMes(vec[1]) + "/" + vec[5];
        return fecha;
    }

    public String MapMes(String mesTexto) {
        switch (mesTexto.toUpperCase().trim()) {
            case "JAN":
                return "01";
            case "FEB":
                return "02";
            case "MAR":
                return "03";
            case "APR":
                return "04";
            case "MAY":
                return "05";
            case "JUN":
                return "06";
            case "JUL":
                return "07";
            case "AUG":
                return "08";
            case "SEP":
                return "09";
            case "OCT":
                return "10";
            case "NOV":
                return "11";
            case "DEC":
                return "12";
        }
        return "";
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public byte[] getFotoPerfil() {
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

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public ConsultaInicial getConsultaInicial() {
        return consultaInicial;
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

    public void setDeParte(String deParte) {
        DeParte = deParte;
    }

    public void setOtros(String otros) {
        Otros = otros;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
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
