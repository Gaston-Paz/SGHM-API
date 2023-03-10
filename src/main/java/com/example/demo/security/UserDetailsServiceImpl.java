package com.example.demo.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.excepciones.BadRequestException;
import com.example.demo.excepciones.Conflict;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository _UsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = _UsuarioRepository.findOneByEmail(email)
                .orElseThrow(() -> new Conflict("El usuario con email " + email + " no existe."));
        return new UserDetailsImpl(usuario);
    }

    public Usuario guardarUsuario(Usuario usuario, boolean recupera) {
        try {
            if (UsuarioExiste(usuario) && !recupera) {
                throw new Conflict(
                        "Ya existe un usuario con este E-mail: " + usuario.getEmail());
            }
            usuario = _UsuarioRepository.save(usuario);
            return usuario;
        } catch (Conflict con) {
            throw con;
        } catch (BadRequestException bad) {
            throw bad;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean UsuarioExiste(Usuario usuario) {
        boolean existe = false;
        ArrayList<Usuario> lista = (ArrayList<Usuario>) _UsuarioRepository.findAll();
        Iterator<Usuario> it = lista.iterator();
        while (it.hasNext()) {
            Usuario aux = it.next();
            if (usuario.getEmail() == aux.getEmail()) {
                existe = true;
            }

        }

        return existe;
    }

    public Optional<Usuario> ObtenerUsuario(String email) {
        return _UsuarioRepository.findOneByEmail(email);
    }

    public ArrayList<Usuario> ObtenerUsuarios() {
        return (ArrayList<Usuario>) _UsuarioRepository.findAll();
    }

    public void EliminarUsuario(Long id) {
        _UsuarioRepository.deleteById(id);
    }
}
