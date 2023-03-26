package com.example.demo.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "ConsultasIniciales")
public class ConsultaInicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @JoinColumn(name = "id_paciente", unique = true)
    @OneToOne()
    private Paciente paciente;

    @Column(nullable = false)
    private Date Fecha;

    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String Motivo;

    @Column()
    private Date FechaCovid;

    @Column()
    private String Otros;

    @Column(nullable = false)
    private String Antiguedad;

    @Column(nullable = false)
    private String Localizacion;

    @Column()
    private String Intensidad;

    @Column()
    private String Caracteristica;

    @Column()
    private String Irradiacion;

    @Column()
    private String Atenua;

    @Column()
    private String ActividadFisica;

    @Column()
    private boolean Covid;

    public boolean getCovid() {
        return Covid;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public Date getFechaCovid() {
        return FechaCovid;
    }

    public String getOtros() {
        return Otros;
    }

    public String getActividadFisica() {
        return ActividadFisica;
    }

    public String getAntiguedad() {
        return Antiguedad;
    }

    public String getAtenua() {
        return Atenua;
    }

    public String getCaracteristica() {
        return Caracteristica;
    }

    public Date getFecha() {
        return Fecha;
    }

    public String getIntensidad() {
        return Intensidad;
    }

    public String getIrradiacion() {
        return Irradiacion;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public String getMotivo() {
        return Motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setActividadFisica(String actividadFisica) {
        ActividadFisica = actividadFisica;
    }

    public void setAntiguedad(String antiguedad) {
        Antiguedad = antiguedad;
    }

    public void setFechaCovid(Date fechaCovid) {
        FechaCovid = fechaCovid;
    }

    public void setOtros(String otros) {
        Otros = otros;
    }

    public void setAtenua(String atenua) {
        Atenua = atenua;
    }

    public void setCaracteristica(String caracteristica) {
        Caracteristica = caracteristica;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setCovid(boolean covid) {
        Covid = covid;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public void setIntensidad(String intensidad) {
        Intensidad = intensidad;
    }

    public void setIrradiacion(String irradiacion) {
        Irradiacion = irradiacion;
    }

    public void setLocalizacion(String localizacion) {
        Localizacion = localizacion;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
