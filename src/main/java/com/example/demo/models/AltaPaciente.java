package com.example.demo.models;

public class AltaPaciente {
    private Paciente paciente;
    private ConsultaInicial consultaInicial;
    private Antecedente antecedente;

    public Antecedente getAntecedente() {
        return antecedente;
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

    public void setConsultaInicial(ConsultaInicial consultaInicial) {
        this.consultaInicial = consultaInicial;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
