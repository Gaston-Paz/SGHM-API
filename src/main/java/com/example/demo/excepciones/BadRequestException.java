package com.example.demo.excepciones;

public class BadRequestException extends RuntimeException {
    private static final String Descripcion = "Bad Request Exception (400)";

    public BadRequestException(String datail) {
        super(Descripcion + ". " + datail);
    }
}
