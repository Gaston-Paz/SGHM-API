package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.*;

@Entity
@Table(name = "Antecedentes")
public class Antecedente {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "antecedente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Paciente paciente;

    @Column(length = Integer.MAX_VALUE)
    private String Cirugias;

    @Column()
    @Value("${Contencion:true}")
    private boolean Contencion;

    @Column(length = Integer.MAX_VALUE)
    private String ImplanteSuperior;

    @Column(length = Integer.MAX_VALUE)
    private String ImplanteInferior;

    @Column(length = Integer.MAX_VALUE)
    private boolean Ortodoncia;

    @Column(length = Integer.MAX_VALUE)
    private String EdadOrtodoncia;

    @Column(length = Integer.MAX_VALUE)
    private String PiezasFaltantesSup;

    @Column(length = Integer.MAX_VALUE)
    private String PiezasFaltantesInf;

    @Column(length = Integer.MAX_VALUE)
    private boolean PlacaDescanso;

    @Column(length = Integer.MAX_VALUE)
    private String Intestinal;

    @Column(length = Integer.MAX_VALUE)
    private String Digestivo;

    @Column(length = Integer.MAX_VALUE)
    private String Cardiaco;

    @Column(length = Integer.MAX_VALUE)
    private String Oseo;

    @Column(length = Integer.MAX_VALUE)
    private String Urogenital;

    @Column(length = Integer.MAX_VALUE)
    private String Respiratorio;

    @Column(length = Integer.MAX_VALUE)
    private String Medicacion;

    @Column(length = Integer.MAX_VALUE)
    private String Otros;

    @Column(length = Integer.MAX_VALUE)
    private String Fracturas;

    @Column(length = Integer.MAX_VALUE)
    private String DolorCabeza;

    @Column(length = Integer.MAX_VALUE)
    private String Alimentacion;

    @Column()
    private boolean Embarazos;

    @Column(length = Integer.MAX_VALUE)
    private String Partos;

    @Column(length = Integer.MAX_VALUE)
    private String AbortosInducido;

    @Column(length = Integer.MAX_VALUE)
    private String AbortosEspontaneo;

    @Column()
    private boolean Menstruacion;

    @Column(length = Integer.MAX_VALUE)
    private String Frecuencia;

    @Column(length = Integer.MAX_VALUE)
    private String Duracion;

    @Column(length = Integer.MAX_VALUE)
    private String Protesis;

    @Column(length = Integer.MAX_VALUE)
    private String Volumen;

    @Column(length = Integer.MAX_VALUE)
    private String Fuma;

    @Column(length = Integer.MAX_VALUE)
    private String OtrasDrogas;

    @Column(length = Integer.MAX_VALUE)
    private String Perdidas;

    @Column(length = Integer.MAX_VALUE)
    private String Accidentes;

    @Column(length = Integer.MAX_VALUE)
    private String Tiroides;

    @Column()
    private boolean Diabetes;

    public String getAbortosInducido() {
        return AbortosInducido;
    }

    public String getOseo() {
        return Oseo;
    }

    public String getAbortosEspontaneo() {
        return AbortosEspontaneo;
    }

    public Long getIdAntecedente() {
        return id;
    }

    public String getFuma() {
        return Fuma;
    }

    public boolean getPlacaDescanso() {
        return PlacaDescanso;
    }

    public String getOtrasDrogas() {
        return OtrasDrogas;
    }

    public String getAccidentes() {
        return Accidentes;
    }

    public boolean getContencion() {
        return Contencion;
    }

    public String getAlimentacion() {
        return Alimentacion;
    }

    public String getCardiaco() {
        return Cardiaco;
    }

    public String getCirugias() {
        return Cirugias;
    }

    public String getDigestivo() {
        return Digestivo;
    }

    public String getProtesis() {
        return Protesis;
    }

    public String getDolorCabeza() {
        return DolorCabeza;
    }

    public String getDuracion() {
        return Duracion;
    }

    public String getEdadOrtodoncia() {
        return EdadOrtodoncia;
    }

    public String getFracturas() {
        return Fracturas;
    }

    public String getFrecuencia() {
        return Frecuencia;
    }

    public String getImplanteInferior() {
        return ImplanteInferior;
    }

    public String getImplanteSuperior() {
        return ImplanteSuperior;
    }

    public String getIntestinal() {
        return Intestinal;
    }

    public String getMedicacion() {
        return Medicacion;
    }

    public String getOtros() {
        return Otros;
    }

    public boolean getOrtodoncia() {
        return Ortodoncia;
    }

    public boolean getDiabetes() {
        return Diabetes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getPartos() {
        return Partos;
    }

    public String getPerdidas() {
        return Perdidas;
    }

    public String getPiezasFaltantesInf() {
        return PiezasFaltantesInf;
    }

    public String getPiezasFaltantesSup() {
        return PiezasFaltantesSup;
    }

    public String getRespiratorio() {
        return Respiratorio;
    }

    public String getTiroides() {
        return Tiroides;
    }

    public String getUrogenital() {
        return Urogenital;
    }

    public boolean getEmbarazos() {
        return Embarazos;
    }

    public boolean getMenstruacion() {
        return Menstruacion;
    }

    public String getVolumen() {
        return Volumen;
    }

    public void setAbortosEspontaneo(String abortosEspontaneo) {
        AbortosEspontaneo = abortosEspontaneo;
    }

    public void setFuma(String fuma) {
        Fuma = fuma;
    }

    public void setOtrasDrogas(String otrasDrogas) {
        OtrasDrogas = otrasDrogas;
    }

    public void setOseo(String oseo) {
        Oseo = oseo;
    }

    public void setAbortosInducido(String abortosInducido) {
        AbortosInducido = abortosInducido;
    }

    public void setProtesis(String protesis) {
        Protesis = protesis;
    }

    public void setIdAntecedente(Long idAntecedente) {
        this.id = idAntecedente;
    }

    public void setAccidentes(String accidentes) {
        Accidentes = accidentes;
    }

    public void setContencion(boolean contencion) {
        Contencion = contencion;
    }

    public void setAlimentacion(String alimentacion) {
        Alimentacion = alimentacion;
    }

    public void setCardiaco(String cardiaco) {
        Cardiaco = cardiaco;
    }

    public void setCirugias(String cirugias) {
        Cirugias = cirugias;
    }

    public void setDiabetes(boolean diabetes) {
        Diabetes = diabetes;
    }

    public void setDigestivo(String digestivo) {
        Digestivo = digestivo;
    }

    public void setDolorCabeza(String dolorCabeza) {
        DolorCabeza = dolorCabeza;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public void setEdadOrtodoncia(String edadOrtodoncia) {
        EdadOrtodoncia = edadOrtodoncia;
    }

    public void setEmbarazos(boolean embarazos) {
        Embarazos = embarazos;
    }

    public void setFracturas(String fracturas) {
        Fracturas = fracturas;
    }

    public void setFrecuencia(String frecuencia) {
        Frecuencia = frecuencia;
    }

    public void setImplanteInferior(String implanteInferior) {
        ImplanteInferior = implanteInferior;
    }

    public void setImplanteSuperior(String implanteSuperior) {
        ImplanteSuperior = implanteSuperior;
    }

    public void setIntestinal(String intestinal) {
        Intestinal = intestinal;
    }

    public void setMedicacion(String medicacion) {
        Medicacion = medicacion;
    }

    public void setMenstruacion(boolean menstruacion) {
        Menstruacion = menstruacion;
    }

    public void setOrtodoncia(boolean ortodoncia) {
        Ortodoncia = ortodoncia;
    }

    public void setOtros(String otros) {
        Otros = otros;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setPartos(String partos) {
        Partos = partos;
    }

    public void setPerdidas(String perdidas) {
        Perdidas = perdidas;
    }

    public void setPiezasFaltantesInf(String piezasFaltantesInf) {
        PiezasFaltantesInf = piezasFaltantesInf;
    }

    public void setPiezasFaltantesSup(String piezasFaltantesSup) {
        PiezasFaltantesSup = piezasFaltantesSup;
    }

    public void setPlacaDescanso(boolean placaDescanso) {
        PlacaDescanso = placaDescanso;
    }

    public void setRespiratorio(String respiratorio) {
        Respiratorio = respiratorio;
    }

    public void setTiroides(String tiroides) {
        Tiroides = tiroides;
    }

    public void setUrogenital(String urogenital) {
        Urogenital = urogenital;
    }

    public void setVolumen(String volumen) {
        Volumen = volumen;
    }
}
