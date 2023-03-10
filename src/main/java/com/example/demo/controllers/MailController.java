package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.excepciones.Conflict;
import com.example.demo.excepciones.Error;
import com.example.demo.models.Recuperacion;
import com.example.demo.models.Recupero;
import com.example.demo.models.Usuario;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.services.MailService;
import com.example.demo.services.RecuperacionService;

import jakarta.transaction.Transactional;

@Controller
@CrossOrigin
@RequestMapping("api/recuperacion")
public class MailController {

    @Autowired
    MailService _mailService;

    @Autowired
    UserDetailsServiceImpl _usuarioService;

    @Autowired
    RecuperacionService _RecuperacionService;

    @PostMapping(path = "{mail}")
    @Transactional
    public ResponseEntity<?> EnviarCodigo(@PathVariable("mail") String mail) {
        try {
            Optional<Usuario> usuario = _usuarioService.ObtenerUsuario(mail);
            if (!usuario.isPresent()) {
                Conflict con = new Conflict("El email ingresado no corresponde a ningún usuario.");
                throw con;
            }
            return _mailService.EnviarMail(mail, usuario.get());
        } catch (Exception e) {
            Conflict err = new Conflict(e.getMessage());
            throw err;
        }

    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> RecuperarContraseña(@RequestBody Recupero recuperacion) {
        try {
            Optional<Usuario> usuario = _usuarioService.ObtenerUsuario(recuperacion.getEmail());
            if (!usuario.isPresent()) {
                Conflict con = new Conflict("El email ingresado no corresponde a ningún usuario.");
                throw con;
            }

            Recuperacion recu = _RecuperacionService.obtenerPorEmail(recuperacion.getEmail());
            if (recu == null) {
                Conflict con = new Conflict("El email ingresado no tiene ningún código de recuperacion activo.");
                throw con;
            }

            if (recu.getToken().equals(recuperacion.getToken())) {
                recu.setActivo(false);
                _RecuperacionService.GuardarRecuperacion(recu);
                usuario.get().setPassword((new BCryptPasswordEncoder().encode(recuperacion.getPassword())));
                _usuarioService.guardarUsuario(usuario.get(), true);
            } else {
                Conflict con = new Conflict("El código de recuperación no coincide. Vuelva a solicitarlo.");
                throw con;
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }

    }

}
