package com.example.demo.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "ConsultasIniciales")
public class ConsultaInicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdConsulta;

    @JoinColumn(name = "id_paciente")
    @OneToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    @Column()
    private Date Fecha;

    @Column()
    private String Motivo;

    @Column()
    private String Antiguedad;

    @Column()
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

    public long getIdConsulta() {
        return IdConsulta;
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

    public void setAtenua(String atenua) {
        Atenua = atenua;
    }

    public void setCaracteristica(String caracteristica) {
        Caracteristica = caracteristica;
    }

    public void setCovid(boolean covid) {
        Covid = covid;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public void setIdConsulta(long idConsulta) {
        IdConsulta = idConsulta;
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
