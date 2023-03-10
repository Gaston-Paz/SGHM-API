package com.example.demo.services;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.models.Recuperacion;
import com.example.demo.models.Usuario;

@Service
public class MailService {

    @Autowired
    private JavaMailSender Mail;

    @Autowired
    private RecuperacionService _RecuperacionService;

    public ResponseEntity<?> EnviarMail(String direccion, Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(direccion);
        email.setFrom("sistemadegestiondehistoriasclinicas@outlook.com");
        email.setSubject("Recuperación de contraseña");
        String token = this.getRandomString(15);
        String saludo = "Hola, " + usuario.getNombre() + usuario.getApellido() + "!";
        String cuerpo = "El siguiente código deberás ingresarlo en el Sistema de Gestión de Historias Clínicas para poder modificar tu contraseña: "
                + token;
        email.setText(saludo);
        email.setText(cuerpo);
        Mail.send(email);
        this.CrearRecuperacion(direccion, token);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    private String getRandomString(int i) {

        // bind the length
        byte[] bytearray = new byte[256];
        ;
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        // remove all spacial char
        theAlphaNumericS = mystring
                .replaceAll("[^A-Z0-9]", "");

        // random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                            && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }

    private Recuperacion CrearRecuperacion(String direccion, String token) {
        Recuperacion recu = new Recuperacion();
        recu.setActivo(true);
        recu.setEmail(direccion);
        recu.setToken(token);
        return _RecuperacionService.GuardarRecuperacion(recu);
    }
}
