package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.models.Usuario;
import com.example.demo.security.UserDetailsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    UserDetailsServiceImpl _usuarioService;

    @GetMapping("/{mail}")
    public Optional<Usuario> obtenerUsuario(@PathVariable("mail") String mail) {
        try {
            return _usuarioService.ObtenerUsuario(mail);
        } catch (Exception e) {
            throw new BadRequestException(
                    "No se pudo obtener el Usuario. Si persiste el error comuníquese con el Administrador");
        }
    }

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios() {
        try {
            return _usuarioService.ObtenerUsuarios();
        } catch (Exception e) {
            throw new BadRequestException(
                    "No se pudo obtener los Usuarios. Si persiste el error comuníquese con el Administrador");
        }
    }

    @PostMapping()
    public Usuario GuardarUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setPassword((new BCryptPasswordEncoder().encode(usuario.getPassword())));
            return _usuarioService.guardarUsuario(usuario, false);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void EliminarUsuario(@PathVariable("id") Long id) {
        try {
            _usuarioService.EliminarUsuario(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
