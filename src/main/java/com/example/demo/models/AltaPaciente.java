package com.example.demo.models;

public class AltaPaciente {
    private Paciente paciente;
    private ConsultaInicial consultaInicial;
    private Antecedente antecedente;
    private Tratamiento tratamiento;

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public ConsultaInicial getConsultaInicial() {
        return consultaInicial;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setConsultaInicial(ConsultaInicial consultaInicial) {
        this.consultaInicial = consultaInicial;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
