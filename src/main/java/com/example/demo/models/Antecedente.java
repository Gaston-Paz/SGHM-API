package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "Antecedentes")
public class Antecedente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedente;

    @JoinColumn(name = "id_paciente", unique = true)
    @OneToOne()
    private Paciente paciente;

    @Column()
    private String Cirugias;

    @Column()
    private String ImplanteSuperior;

    @Column()
    private String ImplanteInferior;

    @Column()
    private boolean Ortodoncia;

    @Column()
    private int EdadOrtodoncia;

    @Column()
    private String PiezasFaltantes;

    @Column()
    private boolean PlacaDescanso;

    @Column()
    private String Intestinal;

    @Column()
    private String Digestivo;

    @Column()
    private String Cardiaco;

    @Column()
    private String Urogenital;

    @Column()
    private String Respiratorio;

    @Column()
    private String Medicacion;

    @Column()
    private String Otros;

    @Column()
    private String Fracturas;

    @Column()
    private String DolorCabeza;

    @Column()
    private String Alimentacion;

    @Column()
    private boolean Embarazos;

    @Column()
    private String Partos;

    @Column()
    private String Abortos;

    @Column()
    private boolean Menstruacion;

    @Column()
    private String Frecuencia;

    @Column()
    private String Duracion;

    @Column()
    private String Volumen;

    @Column()
    private String Perdidas;

    @Column()
    private String Accidentes;

    @Column()
    private String Tiroides;

    @Column()
    private boolean Diabetes;

    public String getAbortos() {
        return Abortos;
    }

    public Long getIdAntecedente() {
        return idAntecedente;
    }

    public String getAccidentes() {
        return Accidentes;
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

    public String getDolorCabeza() {
        return DolorCabeza;
    }

    public String getDuracion() {
        return Duracion;
    }

    public int getEdadOrtodoncia() {
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

    public Paciente getPaciente() {
        return paciente;
    }

    public String getPartos() {
        return Partos;
    }

    public String getPerdidas() {
        return Perdidas;
    }

    public String getPiezasFaltantes() {
        return PiezasFaltantes;
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

    public String getVolumen() {
        return Volumen;
    }

    public void setAbortos(String abortos) {
        Abortos = abortos;
    }

    public void setIdAntecedente(Long idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public void setAccidentes(String accidentes) {
        Accidentes = accidentes;
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

    public void setEdadOrtodoncia(int edadOrtodoncia) {
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

    public void setPiezasFaltantes(String piezasFaltantes) {
        PiezasFaltantes = piezasFaltantes;
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
