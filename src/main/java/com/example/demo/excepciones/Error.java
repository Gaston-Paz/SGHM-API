package com.example.demo.excepciones;

public class Error {

    private String excepcion;

    private String message;

    private String path;

    public String getExcepcion() {
        return excepcion;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Error(Exception excepcion, String path) {
        this.excepcion = excepcion.getClass().getSimpleName();

        this.message = excepcion.getMessage();

        this.path = path;
    }

    // @Override
    // public String toString(){
    // return "Error"
    // }

}
