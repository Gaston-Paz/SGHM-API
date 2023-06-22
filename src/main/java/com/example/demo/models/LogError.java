package com.example.demo.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogError {
    // Dev
    // private final String Path = "C:\\Log-SGHC\\";
    // Prod
    private final String Path = "/home/SGHM-API/Log-SGHC/";
    String formatoFecha = "dd-MM-yyyy";
    SimpleDateFormat formateadorFecha = new SimpleDateFormat(formatoFecha);

    public LogError(Exception e, String metodo) throws IOException {
        Date fecha = new Date();
        String name = this.formateadorFecha.format(fecha);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        int hora = calendario.get(Calendar.HOUR_OF_DAY); // Obtiene la hora en formato de 24 horas
        int minutos = calendario.get(Calendar.MINUTE); // Obtiene los minutos
        int segundos = calendario.get(Calendar.SECOND);
        String horario = hora + ":" + minutos + ":" + segundos;
        File file = new File(this.Path + name + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        PrintWriter bw = new PrintWriter(fw);
        bw.write(
                "-----------------------------------------------------------------------------"
                        +
                        "\n \n");
        bw.write("Horario                 :" + horario + "\n");
        if (e.getCause() != null)
            bw.write("Causa                   :" + e.getCause().toString() + "\n");
        bw.write("Localización de mensaje :" + metodo + "\n");
        if (e.getMessage() != null)
            bw.write("Mensaje                 :" + e.getMessage() + "\n");
        bw.write("Excepción               : " + e.toString() + "\n");
        bw.write("Stack Trace             : ");
        e.printStackTrace(bw);
        bw.write("\n \n ");
        bw.write(
                "-----------------------------------------------------------------------------"
                        +
                        "\n \n ");
        bw.close();
    }
}
