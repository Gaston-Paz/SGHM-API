package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
        return _usuarioService.ObtenerUsuario(mail);
    }

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios() {
        return _usuarioService.ObtenerUsuarios();
    }

    @PostMapping()
    public Usuario GuardarUsuario(@RequestBody Usuario usuario) {
        usuario.setPassword((new BCryptPasswordEncoder().encode(usuario.getPassword())));
        return _usuarioService.guardarUsuario(usuario, false);
    }

    @DeleteMapping("/{id}")
    public void EliminarUsuario(@PathVariable("id") Long id) {
        _usuarioService.EliminarUsuario(id);
    }
}
