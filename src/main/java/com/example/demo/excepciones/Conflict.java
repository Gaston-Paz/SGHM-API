package com.example.demo.excepciones;

public class Conflict extends RuntimeException {
    private static final String Descripcion = "Conflicto: ";

    public Conflict(String datail) {
        super(Descripcion + datail);
    }
}
