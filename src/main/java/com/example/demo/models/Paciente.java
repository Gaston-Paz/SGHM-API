package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ConsultaInicial consultaInicial;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Antecedente antecedente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tratamiento tratamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estudio estudio;

    public String getApellido() {
        return Apellido;
    }

    public String getCelular() {
        return Celular;
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
